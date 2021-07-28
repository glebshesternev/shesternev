package com.shesternev.multithreading.config;

import com.shesternev.multithreading.SpringBootApp;
import com.shesternev.multithreading.model.Hippodrome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Configuration
public class SpringConfig {
    @Bean
    public CyclicBarrier barrier() {
        return new CyclicBarrier(SpringBootApp.getDefaultHorseCount(), () -> {
            hippodrome().print();
            try {
                TimeUnit.MILLISECONDS.sleep(Hippodrome.getStepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    public Hippodrome hippodrome() {
        Hippodrome.setStepTime(1000);
        return new Hippodrome();
    }
}
