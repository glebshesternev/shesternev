package com.shesternev.cache;

import com.shesternev.cache.caches.CacheAspect;
import com.shesternev.cache.caches.FirstLevelCache;
import com.shesternev.cache.caches.SecondLevelCache;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.MyRepository;
import com.shesternev.cache.repository.UserRepository;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
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
