/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {

    static MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0")
        .withReuse(true)
        .withDatabaseName("test")
        .withUsername("test")
        .withPassword("test");

    static {
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
    }
}
