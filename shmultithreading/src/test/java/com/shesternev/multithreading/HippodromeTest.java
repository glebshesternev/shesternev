package com.shesternev.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shesternev.multithreading.model.Hippodrome;
import com.shesternev.multithreading.model.Horse;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HippodromeTest {

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("Arkady", 3, 10);
        Horse horse2 = new Horse("Aleksey", 3, 0);
        Horse horse3 = new Horse("Gleb", 3, 5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertEquals(hippodrome.getWinner(), horse1);
    }
}
