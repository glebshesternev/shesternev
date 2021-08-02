package com.shesternev.cache.caches;

import com.shesternev.cache.exception.CacheException;

public interface MyCache<K, T> {
    T find(K id) throws CacheException;

    void put(K id, T object);
}
