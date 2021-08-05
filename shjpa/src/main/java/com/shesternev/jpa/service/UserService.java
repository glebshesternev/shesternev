package com.shesternev.jpa.service;

import com.shesternev.jpa.model.User;
import com.shesternev.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
}
