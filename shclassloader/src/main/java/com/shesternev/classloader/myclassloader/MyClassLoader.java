package com.shesternev.classloader.myclassloader;

import java.io.*;


public class MyClassLoader extends ClassLoader {
    private String path;

    public MyClassLoader(String path, ClassLoader parent) {
        super(parent);
        this.path = path;

    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] bytes = fetchClassFromFS(path + className + ".class");
            return defineClass(className, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(className);
        }
    }

    private byte[] fetchClassFromFS(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
            throw new FileNotFoundException("File " + path + " not found");
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not read file " + path);
        }
        is.close();
        return bytes;
    }
}
