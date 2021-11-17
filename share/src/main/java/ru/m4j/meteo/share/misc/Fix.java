/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Fix {

    public static void disableUnsafeWarning() {
        try {
            final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            final Unsafe u = (Unsafe) theUnsafe.get(null);

            final Class<?> cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            final Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
