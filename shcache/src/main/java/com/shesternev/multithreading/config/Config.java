package com.shesternev.multithreading.config;

import com.shesternev.multithreading.caches.FirstLevelCache;
import com.shesternev.multithreading.caches.SecondLevelCache;
import com.shesternev.multithreading.model.User;
import com.shesternev.multithreading.repository.UserRepository;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Config {

    @Value("${user.count}")
    private int userCount;

    @Value("${cache.first.level.lifetime}")
    private int lifetime;

    @Value("${cache.second.level.capacity}")
    private int capacity;


    @Bean
    public FirstLevelCache firstLevelCache() {
        return new FirstLevelCache(lifetime);
    }

    @Bean
    public SecondLevelCache secondLevelCache() {
        return new SecondLevelCache(capacity);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(
            IntStream
                .range(0, userCount)
                .mapToObj(x -> (new User("user" + x)))
                .toList());
    }
}
