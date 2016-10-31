package com.odde.bbuddy.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.parse;
import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Date.from;

public class Formats {
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDayToDate(String day) {
        try {
            return new SimpleDateFormat(DAY).parse(day);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    public static LocalDate parseMonth(String month) {
        return parse(month + "-01", ofPattern(DAY));
    }

    public static LocalDate parseDay(String day) {
        return parse(day, ofPattern(DAY));
    }

    public static Date dateOf(LocalDate date) {
        return from(date.atStartOfDay(systemDefault()).toInstant());
    }
}
