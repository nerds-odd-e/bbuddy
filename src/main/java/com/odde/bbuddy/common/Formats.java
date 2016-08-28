package com.odde.bbuddy.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formats {
    public static final String MONTH = "yyyy-MM";
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDay(String source) {
        try {
            return new SimpleDateFormat(DAY).parse(source);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
}
