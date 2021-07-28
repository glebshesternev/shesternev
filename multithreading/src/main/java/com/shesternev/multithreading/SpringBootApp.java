package com.shesternev.multithreading;

import com.shesternev.multithreading.model.Hippodrome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootApp implements CommandLineRunner {

    private final Hippodrome hippodrome;

    @Autowired
    public SpringBootApp(Hippodrome hippodrome) {
        this.hippodrome = hippodrome;

    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootApp.class, args);
    }


    @Override
    public void run(String... args) {
        hippodrome.run();
        hippodrome.printWinner();
    }
}
