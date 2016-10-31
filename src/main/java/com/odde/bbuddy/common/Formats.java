package com.odde.bbuddy.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Formats {
    public static final String MONTH = "yyyy-MM";
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDay(String day) {
        return parseDate(day, DAY);
    }

    public static LocalDate parseMonth(String month) {
        return LocalDate.parse(month + "-01", DateTimeFormatter.ofPattern(DAY));
    }

    public static LocalDate parseDayToLocalDate(String day) {
        return LocalDate.parse(day, DateTimeFormatter.ofPattern(DAY));
    }

    private static Date parseDate(String source, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

}
