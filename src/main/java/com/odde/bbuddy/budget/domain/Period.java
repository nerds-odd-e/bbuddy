package com.odde.bbuddy.budget.domain;

import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.temporal.ChronoUnit.DAYS;

@Setter
public class Period {

    @DateTimeFormat(pattern = DAY)
    private Date startDate;

    @DateTimeFormat(pattern = DAY)
    private Date endDate;

    private LocalDate localDateOf(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private LocalDate getEndDate() {
        return localDateOf(endDate);
    }

    private LocalDate getStartDate() {
        return localDateOf(startDate);
    }

    public long overlappingDayCount(Period anotherPeriod) {
        LocalDate overlappingStartDate = getStartDate().isAfter(anotherPeriod.getStartDate()) ? getStartDate() : anotherPeriod.getStartDate();
        LocalDate overlappingEndDate = getEndDate().isBefore(anotherPeriod.getEndDate()) ? getEndDate() : anotherPeriod.getEndDate();
        return DAYS.between(overlappingStartDate, overlappingEndDate) + 1;
    }
}
