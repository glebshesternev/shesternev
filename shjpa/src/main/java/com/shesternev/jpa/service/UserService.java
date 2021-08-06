package com.shesternev.jpa.service;

import com.shesternev.jpa.model.User;

public interface UserService {
    void add(User user);
    User getById(long id);
    void update(User user);
}
