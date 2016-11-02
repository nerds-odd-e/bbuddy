package com.odde.bbuddy.common.validator;

import org.junit.Test;

import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.parseDay;
import static java.time.ZoneId.systemDefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PastValidatorForLocalDateTest {

    LocalDate today = parseDay("2016-07-01");
    PastValidatorForLocalDate validator = new PastValidatorForLocalDate(stubClock(today));
    ConstraintValidatorContext stubConstraintValidatorContext = mock(ConstraintValidatorContext.class);

    @Test
    public void future_local_date_return_false() {
        LocalDate future = parseDay("2016-07-02");

        assertThat(valid(future)).isEqualTo(false);
    }

    @Test
    public void past_local_date_return_true() {
        LocalDate past = parseDay("2016-06-30");

        assertThat(valid(past)).isEqualTo(true);
    }
    
    @Test
    public void today_return_true() {
        assertThat(valid(today)).isEqualTo(true);
    }
    
    @Test
    public void null_return_true() {
        assertThat(valid(null)).isEqualTo(true);
    }

    private Clock stubClock(LocalDate today) {
        return Clock.fixed(today.atStartOfDay(systemDefault()).toInstant(), systemDefault());
    }

    private boolean valid(LocalDate dateToBeValidated) {
        return validator.isValid(dateToBeValidated, stubConstraintValidatorContext);
    }

}