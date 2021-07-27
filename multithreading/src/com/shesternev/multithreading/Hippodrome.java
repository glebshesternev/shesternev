package com.shesternev.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Hippodrome {

    public static Hippodrome game;

    private static final int stepTime = 500;

    private List<Horse> horses = new ArrayList<>();

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() {
        CyclicBarrier barrier = new CyclicBarrier(horses.size(), () -> {
            print();
            try {
                TimeUnit.MILLISECONDS.sleep(stepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Horse.setBarrier(barrier);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Horse horse : horses){
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
        for (Horse horse : horses)
            if (horse.getDistance() > maxDistance){
                maxDistance = horse.getDistance();
                winner = horse;
            }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName());
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        game.setHorses(Arrays.asList(
                new Horse("horse1", 3, 0 ),
                new Horse("horse2", 3, 0 ),
                new Horse("horse3", 3, 0 )));
        game.run();
        game.printWinner();

    }
}
