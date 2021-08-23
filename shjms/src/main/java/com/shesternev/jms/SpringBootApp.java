package com.shesternev.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootApp implements CommandLineRunner {

    private final RabbitTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) {

        template.setExchange("exchange");
        for (int i = 0; i < 20; i++) {
            template.convertAndSend(i);
        }
    }
}
