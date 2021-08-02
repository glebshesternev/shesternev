package com.shesternev.jdbcCrud.repository;

import java.util.List;

public interface MyCrudRepository<K, T> {
    T get(K id);

    List<T> getAll();

    void create(T object);

    void update(T object);

    void delete(K id);
}
