package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.UserDto;
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
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(UserDto::new)
                             .toList();
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = userDto.toUser();
        userRepository.save(user);
        userDto.setId(user.getId());

    }

    @Override
    public UserDto getUserById(long id) {

        return new UserDto(userRepository.findById(id)
                                         .orElseThrow());
    }

    @Override
    public void updateUser(long id, UserDto user) {
        user.setId(id);
        userRepository.save(user.toUser());
    }

    @Override
    public Address getUserHomeAddress(long id) {
        return userRepository.getById(id).getHomeAddress();
    }

    @Override
    public Address getUserShippingAddress(long id) {
        return userRepository.getById(id).getShippingAddress();
    }

    @Override
    public BillingDetails getUserBillingDetails(long id) {
        return userRepository.getById(id).getBillingDetails();
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
        User user = userRepository.findById(id)
                                  .orElseThrow();
        user.setBillingDetails(billingDetails);
        userRepository.save(user);
    }
}
