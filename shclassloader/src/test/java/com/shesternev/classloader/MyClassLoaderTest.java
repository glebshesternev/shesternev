package com.shesternev.classloader;

import com.shesternev.classloader.myclassloader.MyClassLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyClassLoaderTest {

    MyClassLoader classLoader = new MyClassLoader("", ClassLoader.getSystemClassLoader());

    @Test
    public void loadClass() throws ClassNotFoundException {
        String clazzName = "MyArrayList";
        Class<?> clazz = classLoader.loadClass(clazzName);
        assertEquals(clazzName, clazz.getName());
    }

    @Test
    public void loadClassFromPackage() throws ClassNotFoundException {
        String clazzName = "com.shesternev.multithreading.model.Horse";
        Class<?> clazz = classLoader.loadClass(clazzName);
        assertEquals(clazzName, clazz.getName());
    }

}
