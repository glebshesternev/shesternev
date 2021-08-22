package com.shesternev.cache;

import com.shesternev.cache.caches.FirstLevelCache;
import com.shesternev.cache.caches.SecondLevelCache;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.MyRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Config.class)
public class CacheTest {

    @Autowired
    private MyRepository<String, User> userRepository;

    @Autowired
    private FirstLevelCache firstLevelCache;

    @Autowired
    private SecondLevelCache secondLevelCache;

    @After
    public void clearCache() {
        firstLevelCache.clear();
        secondLevelCache.clear();
    }

    @Test
    public void firstLevelCache() {
        userRepository.get("user0");
        User user = userRepository.get("user0");
        Assert.assertEquals("first", user.getMarker());
    }

    @Test
    public void secondLevelCache() throws InterruptedException {
        userRepository.get("user0");
        Thread.sleep(3000);
        User user = userRepository.get("user0");
        Assert.assertEquals("second", user.getMarker());
    }

    @Test
    public void secondLevelCacheOverflow() throws InterruptedException {
        for (int i = 0; i < 300; i++) {
            userRepository.get("user" + i);
        }
        Thread.sleep(3000);
        User user = userRepository.get("user0");
        System.out.println(user.getMarker());
        Assert.assertEquals("repo", user.getMarker());
    }
}
