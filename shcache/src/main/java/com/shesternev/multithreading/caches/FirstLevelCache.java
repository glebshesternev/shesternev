package com.shesternev.multithreading.caches;

import com.shesternev.multithreading.exception.CacheException;
import com.shesternev.multithreading.model.User;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Aspect
public class FirstLevelCache {

    @Autowired
    private SecondLevelCache secondCache;
    private final int lifetime;
    private final Map<String, Pair<User, LocalTime>> cache;

    @Pointcut("execution(* com.shesternev.multithreading.repository.UserRepository.get(String)) && args(name)")
    public void getUser(String name) {

    }

    public FirstLevelCache(int lifetime) {
        this.lifetime = lifetime;
        cache = new HashMap<>();
    }

    @Around(value = "getUser(name)", argNames = "joinPoint,name")
    public User proxyGetUser(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        try {
            if (cache.containsKey(name)) {
                User user = cache.get(name).getKey();
                user.setMarker("firstCache");
                return user;
            } else {
                return secondCache.find(name);
            }
        } catch (CacheException e) {
            User user = (User) joinPoint.proceed();
            cache.put(name, new Pair<>(user, LocalTime.now()));
            return user;
        }
    }

    @Scheduled(fixedRate = 1000)
    private void clear() {
        Iterator<Entry<String, Pair<User, LocalTime>>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Pair<User, LocalTime>> entry = iterator.next();
            if ((LocalTime.now().getSecond() - entry.getValue().getValue().getSecond()) > lifetime) {
                secondCache.put(entry.getKey(), entry.getValue().getKey());
                iterator.remove();
            }
        }
    }

}
