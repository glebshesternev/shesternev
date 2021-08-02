package com.shesternev.jdbcCrud;


import com.shesternev.jdbcCrud.model.User;
import com.shesternev.jdbcCrud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringBootApp implements CommandLineRunner {


    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) {
        userService.create(User.builder()
            .id(1)
            .name("test")
            .email("test")
            .password("test")
            .build());
    }
}

