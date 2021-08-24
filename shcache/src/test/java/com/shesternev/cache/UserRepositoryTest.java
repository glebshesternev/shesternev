package com.shesternev.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.shesternev.cache.exception.UserException;
import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.UserRepository;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private UserRepository repository = new UserRepository();

    @Test
    public void addThrowUserException() {
        User user = new User("1");
        assertThrows(UserException.class, () -> repository.add(user));
    }

    @Test
    public void getThrowUserException() {
        assertThrows(UserException.class, () -> repository.get("user"));
    }

    @Test
    public void addAndGet() {
        String name = "Arkady";
        User user = new User(name);
        repository.add(user);
        assertEquals(user, repository.get(name));
    }
}
