/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ObjectSerializator<T> {

    public List<T> readCsvFromFileV1(final File file, final Class<T> clazz) throws IOException {
        try (final FileInputStream fis = new FileInputStream(file)) {
            final CsvMapper mapperCsv = new CsvMapper();
            final CsvSchema schema = mapperCsv.schemaFor(clazz).withHeader().withColumnReordering(true).withColumnSeparator('\t');
            final ObjectReader reader = mapperCsv.readerFor(clazz).with(schema);
            return reader.<T>readValues(fis).readAll();
        }
    }

    public List<T> readCsvFromFileV2(final InputStream stream, final Class<T> clazz) throws IOException {
        final CsvMapper mapperCsv = new CsvMapper();
        final CsvSchema schema = mapperCsv.schemaFor(clazz).withHeader().withColumnReordering(true).withColumnSeparator('\t');
        final ObjectReader reader = mapperCsv.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

}
