package com.odde.bbuddy.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formats {
    public static final String MONTH = "yyyy-MM";
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDay(String day) {
        return parseDate(day, DAY);
    }

    public static Date parseMonth(String month) {
        return parseDate(month, MONTH);
    }

    private static Date parseDate(String source, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

}
