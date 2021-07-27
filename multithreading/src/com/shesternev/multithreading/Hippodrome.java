package com.shesternev.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses = new ArrayList<>();

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            move();
            print();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
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
