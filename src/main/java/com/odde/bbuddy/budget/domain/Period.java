package com.odde.bbuddy.budget.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class Period {

    @DateTimeFormat(pattern = DAY)
    private LocalDate startDate;

    @DateTimeFormat(pattern = DAY)
    private LocalDate endDate;

    public long overlappingDayCount(Period anotherPeriod) {
        LocalDate overlappingStartDate = startDate.isAfter(anotherPeriod.startDate) ? startDate : anotherPeriod.startDate;
        LocalDate overlappingEndDate = endDate.isBefore(anotherPeriod.endDate) ? endDate : anotherPeriod.endDate;
        return DAYS.between(overlappingStartDate, overlappingEndDate) + 1;
    }
}
