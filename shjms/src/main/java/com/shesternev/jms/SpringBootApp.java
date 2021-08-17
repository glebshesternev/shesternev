package com.shesternev.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
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
