package com.odde.bbuddy.budget.domain;

import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;

@Setter
public class Period {

    @DateTimeFormat(pattern = DAY)
    private Date startDate;

    @DateTimeFormat(pattern = DAY)
    private Date endDate;
}
