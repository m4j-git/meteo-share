/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChipperTest {

    @Test
    public void testEqualsEncryptDecrypt() throws Exception {
        final String data = "qwerty";
        final Chipper chipper = new Chipper("password", "salt");
        final String value = chipper.encrypt(data);
        final String res = chipper.decrypt(value);
        assertEquals(data, res);
    }

}
