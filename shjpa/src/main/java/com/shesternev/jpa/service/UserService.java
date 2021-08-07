package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(long id);

    void updateUser(long id, User user);

    void updateUserName(long id, String firstName, String lastName);

    void updateUserHomeAddress(long id, Address address);

    void updateUserShippingAddress(long id, Address address);

    default UserDto convertUserToDto(User user) {
        return new UserDto(user);
    }
    default User convertDtoToUser(UserDto userDto) {
        return userDto.toUser();
    }
}
