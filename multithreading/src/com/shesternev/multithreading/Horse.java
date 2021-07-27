package com.shesternev.multithreading;

public class Horse {
    private String name;
    private int speed;
    private long distance;

    public Horse(String name, int speed, long distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void move() {
        distance+=(speed * Math.random());
    }

    public void print() {
        for (int i = 0; i < distance; i++) {
            System.out.print('.');
        }
        System.out.println(name);
    }
}
