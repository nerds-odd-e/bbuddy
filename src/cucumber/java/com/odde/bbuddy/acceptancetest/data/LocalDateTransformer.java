package com.odde.bbuddy.acceptancetest.data;

import cucumber.api.Transformer;

import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.parseMonth;

public class LocalDateTransformer extends Transformer<LocalDate> {
    @Override
    public LocalDate transform(String value) {
        return parseMonth(value);
    }
}
