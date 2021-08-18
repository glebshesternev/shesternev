package com.shesternev.cache;

import com.shesternev.cache.exception.UserException;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

public class UserRepositoryTest {

    UserRepository repository = new UserRepository();

    @Test
    public void addThrowUserException() {
        User user = new User("1");
        Assert.assertThrows(UserException.class, () -> repository.add(user));
    }

    @Test
    public void getThrowUserException() {
        Assert.assertThrows(UserException.class, () -> repository.get("user"));
    }

    @Test
    public void addAndGet() {
        String name = "Arkady";
        User user = new User(name);
        repository.add(user);
        Assert.assertEquals(user, repository.get(name));
    }
}
