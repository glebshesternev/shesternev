package com.shesternev.jpa.service;

import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.BillingDetails;
import com.shesternev.jpa.model.User;
import com.shesternev.jpa.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void updateUser(long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void updateUserName(long id, String firstName, String lastName) {
        User user = userRepository.getById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    @Override
    public void updateUserHomeAddress(long id, Address address) {
        User user = userRepository.getById(id);
        user.setHomeAddress(address);
        userRepository.save(user);
    }

    @Override
    public void updateUserShippingAddress(long id, Address address) {
        User user = userRepository.getById(id);
        user.setShippingAddress(address);
        userRepository.save(user);
    }

    @Override
    public void updateUserBillingDetails(long id, BillingDetails billingDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setBillingDetails(billingDetails);
        userRepository.save(user);
    }
}
