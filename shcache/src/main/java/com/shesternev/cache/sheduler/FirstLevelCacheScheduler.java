package com.shesternev.cache.sheduler;

import com.shesternev.cache.caches.FirstLevelCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("cache.first.level.flag")
public record FirstLevelCacheScheduler(FirstLevelCache firstLevelCache) {

    @Scheduled(fixedRate = 1000)
    private void schedulingCache() {
        firstLevelCache.clear();
    }

}
