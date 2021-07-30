package com.shesternev.cache.caches;

import com.shesternev.cache.exception.CacheException;
import com.shesternev.cache.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@RequiredArgsConstructor
public class CacheAspect {

    private final MyCache<String, User> firstLevelCache;
    private final MyCache<String, User> secondLevelCache;

    @Around("execution(* com.shesternev.cache.repository.UserRepository.get(String)) && args(name)")
    public User proxyGetUser(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        try {
            return findFirstLevelCache(name);
        } catch (CacheException firstLevelException) {
           return findSecondLevelCache(joinPoint, name);
        }
    }

    private User findFirstLevelCache(String name) throws CacheException {
        User user = firstLevelCache.find(name);
        user.setMarker("first");
        return user;
    }

    private User findSecondLevelCache(ProceedingJoinPoint joinPoint, String name) throws Throwable {
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

    @Around("execution(* com.shesternev.cache.caches.FirstLevelCache.clear())")
    public void proxyFirstLevelCacheClear(ProceedingJoinPoint joinPoint) throws Throwable {
        List<User> users = (List<User>) joinPoint.proceed();
        users.forEach(x -> secondLevelCache.put(x.getName(), x));
    }

}