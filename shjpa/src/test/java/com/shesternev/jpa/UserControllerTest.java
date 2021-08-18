package com.shesternev.jpa;

import static org.hamcrest.MatcherAssert.assertThat;

import com.shesternev.jpa.dto.UserDto;
import com.shesternev.jpa.model.User;
import com.shesternev.jpa.service.UserService;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    private String userUrl = "/users";
    private User user = new User("Arkady", "Gretskiy");

    public void addUser() {
        UserDto userDto = new UserDto(user);
        userService.addUser(userDto);
    }

    @Test
    public void smokeTest() {
        assertThat(mockMvc, CoreMatchers.notNullValue());
    }

    @SneakyThrows
    @Test
    public void getUser() {
        addUser();
        mockMvc.perform(get(userUrl).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].firstName", is(user.getFirstName())));
    }

    @Test
    public void test(){}

}
