package com.shesternev.cache.sheduler;

import com.shesternev.cache.caches.MyCache;
import com.shesternev.cache.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class FirstLevelCacheScheduler {

    private final MyCache<String, User> firstLevelCache;

    @Scheduled(fixedRate = 1000)
    private void schedulingCache() {
        firstLevelCache.clear();
    }

}
