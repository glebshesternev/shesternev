package com.shesternev.multithreading.model;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import lombok.Data;

@Data
public class User {

    public User(String name) {
        this.name = name;
        this.password = generatePassword();
    }

    private String name;
    private String password;

    //marker who return user
    private String marker = null;

    private static String generatePassword() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
