package com.shesternev.jms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
public class MyListener {

    private int listenerOneCounter = 0;
    private int listenerTwoCounter = 0;

    @RabbitListener(queues = "queue1")
    public void listenerOne(String message) throws InterruptedException {
        listenerOneCounter++;
        log.info("Listener 1 receive from queue 1: " + message + ". Total massages: " + listenerOneCounter);
        Thread.sleep(1000);

    }

    @RabbitListener(queues = "queue2")
    public void listenerTwo(String message) throws InterruptedException {
        listenerTwoCounter++;
        log.info("Listener 2 receive from queue 1: " + message + ". Total massages: " + listenerTwoCounter);
        Thread.sleep(500);
    }

}
