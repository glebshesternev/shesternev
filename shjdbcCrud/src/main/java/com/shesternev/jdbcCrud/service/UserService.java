package com.shesternev.jdbcCrud.service;

import com.shesternev.jdbcCrud.model.User;
import com.shesternev.jdbcCrud.repository.MyCrudRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MyCrudRepository<Integer, User> userRepository;

    public User get(Integer id) {
        return userRepository.get(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
