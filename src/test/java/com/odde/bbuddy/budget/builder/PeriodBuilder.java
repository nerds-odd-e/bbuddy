package com.odde.bbuddy.budget.builder;

import com.odde.bbuddy.budget.domain.Period;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.odde.bbuddy.budget.domain.Period.builder;
import static com.odde.bbuddy.common.Formats.DAY;

public class PeriodBuilder {

    public static Period.PeriodBuilder defaultPeriod() {
        return builder()
                .startDate(localDate("2016-07-01"))
                .endDate(localDate("2016-08-01"));
    }

    public static Period period(String startDate, String endDate) {
        return builder()
                .startDate(localDate(startDate))
                .endDate(localDate(endDate))
                .build();
    }

    private static LocalDate localDate(String text) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(DAY));
    }

}
