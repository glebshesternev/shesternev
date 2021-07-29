package com.shesternev.multithreading.caches;

import com.shesternev.multithreading.exception.CacheException;
import com.shesternev.multithreading.model.User;
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
            User user = cache.get(name);
            user.setMarker("secondCache");
            return user;
        } else {
            throw new CacheException(name + " not found");
        }
    }

    @Override
    public void put(String name, User user) {
        cache.put(name,user);
    }
}
