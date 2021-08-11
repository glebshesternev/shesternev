package com.shesternev.jpa.controller;

import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.BankAccount;
import com.shesternev.jpa.model.BillingDetails;
import com.shesternev.jpa.model.CreditCard;
import com.shesternev.jpa.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/{id}/bd")
    public BillingDetails getUserBillingDetails(@PathVariable long id) {
        return userService.getUserBillingDetails(id);
    }

    @PutMapping(value = "/{id}/ba")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserBillingDetails(@PathVariable long id, @RequestBody @Valid BankAccount billingDetails) {
        userService.updateUserBillingDetails(id, billingDetails);
    }

    @PutMapping(value = "/{id}/cc")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserBillingDetails(@PathVariable long id, @RequestBody @Valid CreditCard billingDetails) {
        userService.updateUserBillingDetails(id, billingDetails);
    }

    @GetMapping(value = "/{id}/home")
    public Address getUserHomeAddress(@PathVariable long id) {
        return userService.getUserHomeAddress(id);
    }

    @GetMapping(value = "/{id}/shipping")
    public Address getUserShippingAddress(@PathVariable long id) {
        return userService.getUserShippingAddress(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserName(@PathVariable long id, @RequestBody @Valid UserDto user) {
        userService.updateUserName(id, user.getFirstName(), user.getLastName());
    }

    @PutMapping(value = "/{id}/home")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserHomeAddress(@PathVariable long id, @RequestBody @Valid Address address) {
        userService.updateUserHomeAddress(id, address);
    }

    @PutMapping(value = "/{id}/shipping")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserShippingAddress(@PathVariable long id, @RequestBody @Valid Address address) {
        userService.updateUserShippingAddress(id, address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto,
                              BindingResult result,
                              HttpServletResponse response) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        userService.addUser(userDto);
        response.setHeader("Location", "/users/" + userDto.getId());
        return userDto;
    }
}
