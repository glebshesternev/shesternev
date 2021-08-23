package com.shesternev.cache.sheduler;

import com.shesternev.cache.caches.MyCache;
import com.shesternev.cache.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class SecondLevelCacheScheduler {

    private final MyCache<String, User> secondLevelCache;

    @Scheduled(fixedRate = 1000000)
    private void schedulingCache() {
        secondLevelCache.clear();
    }
}
