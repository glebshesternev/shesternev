package com.shesternev.cache;


import com.shesternev.cache.model.User;
import com.shesternev.cache.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApp implements CommandLineRunner {

    private final MyRepository<String, User> userRepository;

    @Autowired
    public SpringBootApp(MyRepository<String, User> userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }


    @Override
    public void run(String... args) {

        User user = userRepository.get("user0");
        System.out.println(user.getMarker()); //repo

        user = userRepository.get("user0");
        System.out.println(user.getMarker()); //first

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        user = userRepository.get("user0");
        System.out.println(user.getMarker()); //second


        for (int i = 0; i < 300; i++) {
            userRepository.get("user" + i);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        user = userRepository.get("user0");
        System.out.println(user.getMarker()); //repo

        System.exit(0);
    }
}
