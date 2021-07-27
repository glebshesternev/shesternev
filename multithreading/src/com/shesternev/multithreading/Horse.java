package com.shesternev.multithreading;


import lombok.Data;

public @Data class Horse {
    private String name;
    private int speed;
    private long distance;

    public Horse(String name, int speed, long distance) {
        this.name = name;
        this.speed = speed;
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
