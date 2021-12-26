/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private final String messageKey;
    private final String detail;

    public String getLocalizedMessage(Locale locale) {
        return ResourceBundle.getBundle(ErrorAttribute.PROPERTY_FILE_NAME, locale).getString(messageKey);
    }
}
