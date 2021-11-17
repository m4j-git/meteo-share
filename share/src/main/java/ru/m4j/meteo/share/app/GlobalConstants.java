/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.app;

import java.io.File;

public class GlobalConstants {

    private GlobalConstants() {

    }

    public static final String MIN_TS = "1970-01-01 00:00:01";
    public static final String MAX_TS = "2038-01-19 03:14:07";

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_STAGE = "stage";

    public static final String BASE_PACKAGES = "ru.m4j.meteo";

    public static final String TEST_DATA_PATH = "src" + File.separator + "test" + File.separator + "data" + File.separator;
}
