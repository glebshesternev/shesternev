package com.shesternev.multithreading.repository;

public interface MyRepository<K, T> {
    T get(K id);

    void add(T object);
}
