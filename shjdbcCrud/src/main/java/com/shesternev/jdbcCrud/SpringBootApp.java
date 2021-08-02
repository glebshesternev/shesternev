package com.shesternev.jdbcCrud;



import com.shesternev.jdbcCrud.model.User;
import com.shesternev.jdbcCrud.repository.MyCrudRepository;
import com.shesternev.jdbcCrud.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringBootApp implements CommandLineRunner {


    private UserService userService;

    @Autowired
    public SpringBootApp(UserService userService) {
        this.userService = userService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) throws ClassNotFoundException {
        List<User> list = userService.getAll();
        for (User user:list) {
            log.info(user.getName());
        }
    }
}
