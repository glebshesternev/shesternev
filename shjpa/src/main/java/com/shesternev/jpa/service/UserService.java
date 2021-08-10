package com.shesternev.jpa.service;

import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.BillingDetails;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    void addUser(UserDto user);

    UserDto getUserById(long id);

    void updateUser(long id, UserDto user);

    Address getUserHomeAddress(long id);

    Address getUserShippingAddress(long id);

    BillingDetails getUserBillingDetails(long id);

    void updateUserName(long id, String firstName, String lastName);

    void updateUserHomeAddress(long id, Address address);

    void updateUserShippingAddress(long id, Address address);

    void updateUserBillingDetails(long id, BillingDetails billingDetails);
}
