package com.shesternev.jpa.controller;

import com.shesternev.jpa.model.User;
import com.shesternev.jpa.service.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public @ResponseBody User getUser(@PathVariable long id) {
        return userService.getById(id);
    }

//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateUser(@PathVariable long id,
//                           @RequestBody @Valid User user) {
//        userService.
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User createUser(@RequestBody @Valid User user,
                                         BindingResult result,
                                         HttpServletResponse response) throws BindException {
        if (result.hasErrors()){
            throw new BindException(result);
        }
        userService.add(user);
        response.setHeader("Location", "/users/" + user.getId());
        return user;
    }

}
