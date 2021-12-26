/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    private static final String DIGITIS = "0123456789";
    private static final String ALPHNUM = UPPER + LOWER + DIGITIS;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomString(final int length, final Random random, final String symbols) {
        if ((length < 1) || (symbols.length() < 2)) {
            throw new IllegalArgumentException();
        }
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    public RandomString(final int length, final Random random) {
        this(length, random, ALPHNUM);
    }

    public RandomString(final int length) {
        this(length, new SecureRandom());
    }

    public RandomString() {
        this(21);
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

}
