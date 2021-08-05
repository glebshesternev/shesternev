package com.shesternev.jpa.service;

import com.shesternev.jpa.model.User;

public interface IUserService {
    void add(User user);
    User getById(long id);
}
