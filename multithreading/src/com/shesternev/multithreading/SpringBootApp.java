package com.shesternev.multithreading;

import com.shesternev.multithreading.model.Hippodrome;
import com.shesternev.multithreading.model.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootApp implements CommandLineRunner {

    private static final int DEFAULT_SPEED = 3;
    private static final int DEFAULT_DISTANCE = 0;
    private static final int DEFAULT_HORSE_COUNT = 3;

    public static int getDefaultHorseCount() {
        return DEFAULT_HORSE_COUNT;
    }

    private final Hippodrome hippodrome;

    @Autowired
    public SpringBootApp(Hippodrome hippodrome) {
        this.hippodrome = hippodrome;

        IntStream.rangeClosed(1, DEFAULT_HORSE_COUNT)
                .mapToObj(x -> "horse"+x)
                .forEach(x -> hippodrome.addHorses(new Horse(x, DEFAULT_SPEED, DEFAULT_DISTANCE)));

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
