package com.shesternev.jpa.dto;

import com.shesternev.jpa.model.User;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
        User user = new User(firstName, lastName);
        user.setId(id);
        return user;
    }
}
