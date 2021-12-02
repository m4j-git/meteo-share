package ru.m4j.meteo.share.misc;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String messageKey;
    private String detail;

    public String getLocalizedMessage(Locale locale) {
        return ResourceBundle.getBundle(ErrorAttribute.PROPERTY_FILE_NAME, locale).getString(messageKey);
    }
}
