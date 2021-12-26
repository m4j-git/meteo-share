/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ChipperTest {

    @Test
    @Disabled("fexme")
    void testEqualsEncryptDecrypt() throws Exception {
        String data = "qwerty";
        Chipper chipper = new Chipper("password", "salt");
        String value = chipper.encrypt(data);
        String res = chipper.decrypt(value);
        assertThat(data).isEqualTo(res);
    }

}
