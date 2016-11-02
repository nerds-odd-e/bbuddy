package com.odde.bbuddy.common.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MonthFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<Month> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>() {{
            add(LocalDate.class);
        }};
    }

    @Override
    public Printer<?> getPrinter(Month annotation, Class<?> fieldType) {
        return new MonthFormatter();
    }

    @Override
    public Parser<?> getParser(Month annotation, Class<?> fieldType) {
        return new MonthFormatter();
    }
}
