package com.shesternev.cache;

import com.shesternev.cache.caches.MyCache;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.MyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTest {

    @Autowired
    private MyRepository<String, User> userRepository;

    @Autowired
    @Qualifier("first")
    private MyCache<String, User> firstLevelCache;

    @Autowired
    @Qualifier("second")
    private MyCache<String, User> secondLevelCache;

    @AfterEach
    public void clearCache() {
        firstLevelCache.clear();
        secondLevelCache.clear();
    }

    @Test
    public void firstLevelCache() {
        userRepository.get("user0");
        User user = userRepository.get("user0");
        Assertions.assertEquals("first", user.getMarker());
    }

    @Test
    public void secondLevelCache() throws InterruptedException {
        userRepository.get("user0");
        Thread.sleep(3000);
        User user = userRepository.get("user0");
        Assertions.assertEquals("second", user.getMarker());
    }

    @Test
    public void secondLevelCacheOverflow() throws InterruptedException {
        for (int i = 0; i < 300; i++) {
            userRepository.get("user" + i);
        }
        Thread.sleep(3000);
        User user = userRepository.get("user0");
        System.out.println(user.getMarker());
        Assertions.assertEquals("repo", user.getMarker());
    }
}
