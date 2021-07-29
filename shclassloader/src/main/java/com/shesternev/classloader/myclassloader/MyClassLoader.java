package com.shesternev.classloader.myclassloader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;

public class MyClassLoader extends ClassLoader {
    private final String path;

    public MyClassLoader(String path, ClassLoader parent) {
        super(parent);
        this.path = path;

    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] bytes = fetchClassFromFileStream(path + className + ".class");
            return defineClass(className, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(className);
        }
    }

    private byte[] fetchClassFromFileStream(String path) throws IOException {

        long length = new File(path).length();
        byte[] bytes = new byte[(int) length];
        try (InputStream inputStream = new FileInputStream(path)) {
            if (length > Integer.MAX_VALUE) {
                throw new FileNotFoundException("File " + path + " not found");
            }
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && numRead >= 0) {
                numRead = inputStream.read(bytes, offset, bytes.length - offset);
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not read file " + path);
            }
        }
        return bytes;
    }
}
