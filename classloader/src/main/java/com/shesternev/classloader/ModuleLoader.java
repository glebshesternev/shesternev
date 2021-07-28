package com.shesternev.classloader;

import com.shesternev.classloader.myclassloader.MyClassLoader;

import java.lang.reflect.Method;

public class ModuleLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader("", ClassLoader.getSystemClassLoader());
        Class<?> clazz = classLoader.loadClass("MyArrayList");
        System.out.println(clazz.getName());
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }
    }
}
