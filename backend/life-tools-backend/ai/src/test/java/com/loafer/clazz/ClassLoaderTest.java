package com.loafer.clazz;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author loafer
 * @since 2025-07-08 22:52:52
 **/
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        URLClassLoader classLoader = new URLClassLoader(new URL[0], systemClassLoader);
        System.out.println(classLoader.getParent());
    }
}
