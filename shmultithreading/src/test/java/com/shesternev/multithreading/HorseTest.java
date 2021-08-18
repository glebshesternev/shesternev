package com.shesternev.multithreading;

import com.shesternev.multithreading.model.Horse;
import org.junit.Assert;
import org.junit.Test;

public class HorseTest {

    Horse horse = new Horse("Arkady", 3, 0);

    @Test
    public void move() {
        for (int i = 0; i < 10; i++) {
            horse.move();
        }
        Assert.assertTrue(horse.getDistance() > 0);
    }

    @Test
    public void track() {
        horse.setDistance(3);
        Assert.assertEquals("..." + horse.getName(), horse.track());
    }
}
