package com.shesternev.cache.caches;

import com.shesternev.cache.model.User;
import com.shesternev.cache.exception.CacheException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javafx.util.Pair;


public class FirstLevelCache implements MyCache<String, User> {

    private final int lifetime;
    private final Map<String, Pair<User, LocalTime>> cache;

    public FirstLevelCache(int lifetime) {
        this.lifetime = lifetime;
        cache = new ConcurrentHashMap<>();
    }


    @Override
    public User find(String name) throws CacheException {
        if (cache.containsKey(name)) {
            return cache.get(name).getKey();
        } else {
            throw new CacheException(name + " not found");
        }
    }

    @Override
    public void put(String name, User user) {
        cache.put(name, new Pair<>(user, LocalTime.now()));
    }

    public List<User> clear() {
        return cache.entrySet()
            .stream()
            .filter(this::checkTime)
            .map(x -> x.getValue().getKey())
            .map(this::removeUser)
            .toList();
    }

    private User removeUser(User user) {
        cache.remove(user.getName());
        return user;
    }

    private boolean checkTime(Entry<String, Pair<User, LocalTime>> entry) {
        return (LocalTime.now().getSecond() - entry.getValue().getValue().getSecond()) > lifetime;
    }

}
