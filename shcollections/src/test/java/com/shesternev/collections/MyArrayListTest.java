package com.shesternev.collections;

import com.shesternev.collections.impl.MyArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    private final MyList<Object> list = new MyArrayList<>();

    public void initData(Object... values) {
        for (Object value : values) {
            list.add(value);
        }
    }

    @Test
    public void constructorShouldThrowNegativeArraySizeException() {
        assertThrows(NegativeArraySizeException.class, () -> new MyArrayList(-1));
    }

    @Test
    public void size() {
        int initSize = 3;
        initData(3, 5, 9);
        assertEquals(initSize, list.size());
    }

    @Test
    public void addByIndexShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> list.add(list.size() + 1, 1));
    }

    @Test
    public void get() {
        int value = 1;
        initData(value);
        assertEquals(value, list.get(0));
    }

    @Test
    public void getShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> list.get(list.size() + 1));
    }

    @Test
    public void set() {
        list.set(0, 9);
        assertEquals(9, list.get(0));
    }

    @Test
    public void setShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> list.set(list.size() + 1, 0));
    }

    @Test
    public void removeByIndex() {
        Integer[] simpleData = {1, 2, 3};
        initData(simpleData);
        list.remove(1);
        assertEquals(simpleData.length - 1, list.size());
        assertEquals(simpleData[2], list.get(1));
    }

    @Test
    public void removeByValue() {
        initData(1);
        assertTrue(list.remove(Integer.valueOf(1)));
    }

    @Test
    public void notRemoveByValue() {
        assertFalse(list.remove(Integer.valueOf(4)));
    }

    @Test
    public void removeByIndexShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> list.remove(list.size() + 1));
    }

    @Test
    public void empty() {
        assertTrue(list.isEmpty());
    }
}
