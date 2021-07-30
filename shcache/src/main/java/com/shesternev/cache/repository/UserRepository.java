package com.shesternev.cache.repository;

import com.shesternev.cache.exception.UserException;
import com.shesternev.cache.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements MyRepository<String, User> {

    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public User get(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserException(name + " not found.");
    }

    @Override
    public void add(User user) {
        if (user.getName().length() < 2) {
            throw new UserException("come up with a normal name)");
        }
        users.add(user);
    }
}
