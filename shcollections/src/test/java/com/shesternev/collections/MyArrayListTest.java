package com.shesternev.collections;

import com.shesternev.collections.impl.MyArrayList;
import org.junit.Assert;
import org.junit.Test;

public class MyArrayListTest {

    private final MyList<Integer> list = new MyArrayList<>();

    public void initData(Integer... values) {
        for (Integer value : values) {
            list.add(value);
        }
    }

    @Test
    public void constructorShouldThrowNegativeArraySizeException() {
        Assert.assertThrows(NegativeArraySizeException.class, () -> new MyArrayList(-1));
    }

    @Test
    public void size() {
        Integer[] values = {3, 5, 9};
        initData(values);
        Assert.assertEquals(values.length, list.size());
    }

    @Test
    public void addByIndexShouldThrowIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> list.add(list.size() + 1, 1));
    }

    @Test
    public void get() {
        Integer[] simpleData = {1};
        initData(simpleData);
        Assert.assertEquals(simpleData[0], list.get(0));
    }

    @Test
    public void getShouldThrowIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> list.get(list.size() + 1));
    }

    @Test
    public void set() {
        list.set(0, 9);
        Assert.assertEquals(Integer.valueOf(9), list.get(0));
    }

    @Test
    public void setShouldThrowIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> list.set(list.size() + 1, 0));
    }

    @Test
    public void removeByIndex() {
        Integer[] simpleData = {1, 2, 3};
        initData(simpleData);
        list.remove(1);
        Assert.assertEquals(simpleData.length - 1, list.size());
        Assert.assertEquals(simpleData[2], list.get(1));
    }

    @Test
    public void removeByValue() {
        initData(1);
        Assert.assertTrue(list.remove(Integer.valueOf(1)));
    }

    @Test
    public void notRemoveByValue() {
        Assert.assertFalse(list.remove(Integer.valueOf(4)));
    }

    @Test
    public void removeByIndexShouldThrowIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> list.remove(list.size() + 1));
    }

    @Test
    public void empty() {
        Assert.assertTrue(list.isEmpty());
    }
}
