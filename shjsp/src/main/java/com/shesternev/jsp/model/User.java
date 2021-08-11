package com.shesternev.jsp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String firstName;
    private String lastName;
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

}
