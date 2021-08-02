package com.shesternev.cache.caches;

import com.shesternev.cache.exception.CacheException;
import com.shesternev.cache.model.User;
import java.util.LinkedHashMap;
import java.util.Map;

public class SecondLevelCache implements MyCache<String, User> {

    private final Map<String, User> cache;

    public SecondLevelCache(int capacity) {
        cache = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public User find(String name) throws CacheException {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        throw new CacheException(name + " not found");
    }

    @Override
    public void put(String name, User user) {
        cache.put(name, user);
    }
}
