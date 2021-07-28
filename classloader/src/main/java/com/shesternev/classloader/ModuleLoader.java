package com.shesternev.classloader;

import com.shesternev.classloader.myclassloader.MyClassLoader;

import java.lang.reflect.Method;

public class ModuleLoader {
    public static void main(String[] args) throws ClassNotFoundException {
//        String path = "./classloader/src/main/resources/";
        MyClassLoader classLoader = new MyClassLoader("", ClassLoader.getSystemClassLoader());
        Class clazz = classLoader.loadClass("Horse");
        System.out.println(clazz.getName());
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }
//        File f = new File(path);
//        String[] files = f.list();
//        for (String file : files) {
//            String className = file.split(".class")[0];
//            Class clazz = classLoader.loadClass(file);
//            System.out.println(clazz.getName());
//        }
    }
}
