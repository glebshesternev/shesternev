package com.shesternev.classloader;

import com.shesternev.classloader.myclassloader.MyClassLoader;
import org.junit.Assert;
import org.junit.Test;

public class MyClassLoaderTest {

    MyClassLoader classLoader = new MyClassLoader("", ClassLoader.getSystemClassLoader());

    @Test
    public void loadClass() throws ClassNotFoundException {
        Class<?> clazz = classLoader.loadClass("MyArrayList");
        String clazzName = "MyArrayList";
        Assert.assertEquals(clazzName, clazz.getName());
    }

    @Test
    public void loadClassFromPackage() throws ClassNotFoundException {
        Class<?> clazz = classLoader.loadClass("com.shesternev.multithreading.model.Horse");
        String clazzName = "com.shesternev.multithreading.model.Horse";
        Assert.assertEquals(clazzName, clazz.getName());
    }

}
