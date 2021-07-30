package com.shesternev.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootApp implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("db init");
    }
}
