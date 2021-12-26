/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UuidGenerator {
    public String string() {
        return String.valueOf(UUID.randomUUID());
    }

    public UUID uuid() {
        return UUID.randomUUID();
    }
}
