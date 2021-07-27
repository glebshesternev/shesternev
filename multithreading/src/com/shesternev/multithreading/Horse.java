package com.shesternev.multithreading;


import lombok.Data;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public @Data class Horse implements Runnable {

    private static CyclicBarrier barrier;
    private static int steps = 20;

    public static void setBarrier(CyclicBarrier barrier) {
        Horse.barrier = barrier;
    }

    public static int getSteps() {
        return steps;
    }

    public static void setSteps(int steps) {
        Horse.steps = steps;
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
                synchronized (this) {
                    move();
                }
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
