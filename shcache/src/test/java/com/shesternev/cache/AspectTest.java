package com.shesternev.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shesternev.cache.caches.CacheAspect;
import com.shesternev.cache.caches.FirstLevelCache;
import com.shesternev.cache.caches.SecondLevelCache;
import com.shesternev.cache.model.User;
import java.util.List;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AspectTest {

    @Mock
    private final FirstLevelCache firstLevelCache = new FirstLevelCache(1);

    @Spy
    private final SecondLevelCache secondLevelCache = new SecondLevelCache(100);

    @InjectMocks
    private final CacheAspect cacheAspect = new CacheAspect(true, true);

    private final User user = new User("Arkady");

    @SneakyThrows
    @Test
    public void firstLevelCacheClean() {
        Mockito.when(firstLevelCache.clear()).thenReturn(List.of(user));
        firstLevelCache.clear();
        ProceedingJoinPoint joinPoint = Mockito.mock(ProceedingJoinPoint.class);
        Mockito.when(joinPoint.proceed()).thenReturn(List.of(user));
        cacheAspect.proxyFirstLevelCacheClear(joinPoint);
        assertEquals(user, secondLevelCache.find(user.getName()));

    }
}
