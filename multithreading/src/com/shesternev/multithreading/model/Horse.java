package com.shesternev.multithreading.model;


import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Getter
@Setter
public class Horse implements Runnable {

    private static CyclicBarrier barrier;
    private static int steps = 20;

    public static void setBarrier(CyclicBarrier barrier) {
        Horse.barrier = barrier;
    }

    public static int getSteps() {
        return steps;
    }

    private String name;
    private int speed;
    private long distance;

    public Horse(String name, int speed, long distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void run() {
        try {
            for (int i = 0; i < steps; i++) {
                move();
                barrier.await();
            }
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        distance+=(speed * Math.random());
    }

    public String track() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < distance; i++) {
            sb.append('.');
        }
        sb.append(name);
        return sb.toString();
    }
}
