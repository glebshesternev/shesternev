package com.shesternev.cache.caches;

import com.shesternev.cache.exception.CacheException;
import com.shesternev.cache.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Aspect
@RequiredArgsConstructor
public class CacheAspect {

    @Autowired
    @Qualifier("first")
    private MyCache<String, User> firstLevelCache;
    @Autowired(required = false)
    @Qualifier("second")
    private MyCache<String, User> secondLevelCache;
    private final boolean firstLevelCacheFlag;
    private final boolean secondLevelCacheFlag;



    @Around("execution(* com.shesternev.cache.repository.UserRepository.get(String)) && args(name)")
    public User proxyGetUser(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        if (!firstLevelCacheFlag) {
            User user = (User) joinPoint.proceed();
            user.setMarker("repo");
            return user;
        }
        try {
            return findFirstLevelCache(name);
        } catch (CacheException firstLevelException) {
            if (!secondLevelCacheFlag) {
                User user = (User) joinPoint.proceed();
                firstLevelCache.put(name, user);
                user.setMarker("repo");
                return user;
            }
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
        if (!secondLevelCacheFlag) {
            joinPoint.proceed();
        }
        List<User> users = (List<User>) joinPoint.proceed();
        users.forEach(x -> secondLevelCache.put(x.getName(), x));
    }

}