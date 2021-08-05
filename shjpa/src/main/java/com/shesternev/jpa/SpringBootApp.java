package com.shesternev.jpa;

import com.shesternev.jpa.model.Address;
import com.shesternev.jpa.model.User;
import com.shesternev.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootApp implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.add(new User("test", "test",
//            new Address("test", "123456", "test"),
//            new Address("test", "123456", "test")));
    }
}

