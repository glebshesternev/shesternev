package com.shesternev.multithreading.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Hippodrome {

    @Autowired
    CyclicBarrier barrier;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void setStepTime(int stepTime) {
        Hippodrome.stepTime = stepTime;
    }

    private static int stepTime;

    private ExecutorService executorService;

    private final List<Horse> horses;

    public void run() {
        Horse.setBarrier(barrier);
        executorService = Executors.newCachedThreadPool();
        for (Horse horse : horses) {
            executorService.execute(horse);
        }
        try {
            TimeUnit.MILLISECONDS.sleep((long) stepTime * Horse.getSteps());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            System.out.println(horse.track());
        }
    }

    public Horse getWinner() {
        long maxDistance = 0;
        Horse winner = null;
        for (Horse horse : horses) {
            if (horse.getDistance() > maxDistance) {
                maxDistance = horse.getDistance();
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName());
        executorService.shutdown();
    }

}
