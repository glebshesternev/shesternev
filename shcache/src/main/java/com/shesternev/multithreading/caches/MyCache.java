package com.shesternev.multithreading.caches;

import com.shesternev.multithreading.exception.CacheException;

public interface MyCache<K, T> {
    T find(K id) throws CacheException;

    void put(K id, T object);
}
