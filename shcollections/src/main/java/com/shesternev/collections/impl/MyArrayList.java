package com.shesternev.collections.impl;

import com.shesternev.collections.MyList;

public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private Object[] array;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int size) {
        array = new Object[size];
    }


    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        checkAndIncreaseCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return (T) temp;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return true;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        Object temp = array[index];
        array[index] = element;
        return (T) temp;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index should be positive or zero. Your index:" + index);
        }
        if (index > size) {
            throw new IllegalArgumentException("Index should be less then collection size. Size: " + size + "; Your index: " + index);
        }
    }

    private void checkAndIncreaseCapacity() {
        if (size >= array.length) {
            Object[] temp = array;
            array = new Object[(array.length) * 3 / 2 + 1];
            System.arraycopy(temp, 0, array, 0, temp.length);
        }
    }
}
