package com.shesternev.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.shesternev.multithreading.model.Horse;
import org.junit.jupiter.api.Test;

public class HorseTest {

    Horse horse = new Horse("Arkady", 3, 0);

    @Test
    public void moveIncreaseDistance() {
        horse.move();
        assertTrue(horse.getDistance() > 0);
    }

    @Test
    public void trackReturnCorrectString() {
        horse.setDistance(3);
        assertEquals("..." + horse.getName(), horse.track());
    }
}
