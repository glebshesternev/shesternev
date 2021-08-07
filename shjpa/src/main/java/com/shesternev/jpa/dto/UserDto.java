package com.shesternev.jpa.dto;

import com.shesternev.jpa.model.User;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    protected UserDto() {
    }

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public User toUser() {
        return new User(firstName, lastName);
    }
}
