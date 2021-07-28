package com.shesternev.multithreading.config;

import com.shesternev.multithreading.model.Hippodrome;
import com.shesternev.multithreading.model.Horse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
public class SpringConfig {

    @Value("${spring.hippodrome.stepTime}")
    int stepTime;

    @Value("${default.speed}")
    int speed;

    @Value("${default.distance}")
    int distance;

    @Value("${default.horse.count}")
    int count;


    @Bean
    public CyclicBarrier barrier() {
        return new CyclicBarrier(count, () -> {
            hippodrome().print();
            try {
                TimeUnit.MILLISECONDS.sleep(stepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    public Hippodrome hippodrome() {
        Hippodrome.setStepTime(stepTime);
        return new Hippodrome(IntStream.rangeClosed(1, count)
                .mapToObj(x -> "horse" + x)
                .map(x -> new Horse(x, speed, distance))
                .toList());
    }
}
