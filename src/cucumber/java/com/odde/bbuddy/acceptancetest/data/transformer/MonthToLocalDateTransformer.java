package com.odde.bbuddy.acceptancetest.data.transformer;

import cucumber.api.Transformer;

import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.parseMonth;

public class MonthToLocalDateTransformer extends Transformer<LocalDate> {
    @Override
    public LocalDate transform(String value) {
        return parseMonth(value);
    }
}
