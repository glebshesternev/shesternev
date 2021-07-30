package com.shesternev.cache.caches;

import com.shesternev.cache.exception.CacheException;
import com.shesternev.cache.model.User;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CacheAspect {

    private final MyCache<String, User> firstLevelCache;
    private final MyCache<String, User> secondLevelCache;

    public CacheAspect(MyCache<String, User> firstLevelCache, MyCache<String, User> secondLevelCache) {
        this.firstLevelCache = firstLevelCache;
        this.secondLevelCache = secondLevelCache;
    }

    @Around("execution(* com.shesternev.cache.repository.UserRepository.get(String)) && args(name)")
    public User proxyGetUser(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        try {
            User user = firstLevelCache.find(name);
            user.setMarker("first");
            return user;
        } catch (CacheException firstLevelException) {
            try {
                User user = secondLevelCache.find(name);
                user.setMarker("second");
                return user;
            } catch (CacheException secondLevelException) {
                User user = (User) joinPoint.proceed();
                firstLevelCache.put(name, user);
                user.setMarker("repo");
                return user;
            }
        }
    }

    @Around("execution(* com.shesternev.cache.caches.FirstLevelCache.clear())")
    public void proxyFirstLevelCacheClear(ProceedingJoinPoint joinPoint) throws Throwable {
        List<User> users = (List<User>) joinPoint.proceed();
        users.forEach(x -> secondLevelCache.put(x.getName(), x));
    }

}