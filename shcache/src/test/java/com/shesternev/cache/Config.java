package com.shesternev.cache;

import com.shesternev.cache.caches.CacheAspect;
import com.shesternev.cache.caches.FirstLevelCache;
import com.shesternev.cache.caches.SecondLevelCache;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.UserRepository;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@TestConfiguration
@EnableScheduling
public class Config {

    @Value("${user.count}")
    private int userCount;

    @Value("${cache.first.level.lifetime}")
    private int lifetime;

    @Value("${cache.second.level.capacity}")
    private int capacity;

    @Value("${cache.first.level.flag}")
    private boolean firstLevelCacheFlag;

    @Value("${cache.second.level.flag}")
    private boolean secondLevelCacheFlag;

    @Bean
    @ConditionalOnProperty("cache.first.level.flag")
    public FirstLevelCache firstLevelCache() {
        return new FirstLevelCache(lifetime);
    }

    @Bean
    @ConditionalOnProperty("cache.second.level.flag")
    public SecondLevelCache secondLevelCache() {
        return new SecondLevelCache(capacity);
    }

    @Bean
    @ConditionalOnProperty("cache.first.level.flag")
    public CacheAspect cacheAspect() {
        return new CacheAspect(firstLevelCacheFlag, secondLevelCacheFlag);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(IntStream.range(0, userCount).mapToObj(x -> (new User("user" + x))).toList());
    }
}
