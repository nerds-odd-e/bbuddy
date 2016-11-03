package com.odde.bbuddy.common;

import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NestedRunner.class)
public class FormatsTest {

    public class ParseDayToDate {
        
        @Test
        public void should_parse_correctly() throws ParseException {
            assertThat(parseDayToDate("2013-07-04")).isEqualTo(date("2013-07-04"));
        }
        
        @Test(expected = IllegalStateException.class)
        public void should_throw_exception_when_parse_invalid_date() {
            parseDayToDate("invalid date");
        }
        
    }

    public class ParseMonth {
        
        @Test
        public void should_parse_correctly() {
            assertThat(parseMonth("2016-05")).isEqualTo(localDate("2016-05-01"));
        }
        
    }

    public class ParseDay {

        @Test
        public void should_parse_correctly() {
            assertThat(parseDay("2015-05-01")).isEqualTo(localDate("2015-05-01"));
        }

    }

    public class DateOf {
        
        @Test
        public void should_parse_correctly() throws ParseException {
            assertThat(dateOf(localDate("2014-12-11"))).isEqualTo(date("2014-12-11"));
        }
        
    }

    private LocalDate localDate(String dateStr) {
        return LocalDate.parse(dateStr, ofPattern(DAY));
    }

    private Date date(String dateStr) throws ParseException {
        return new SimpleDateFormat(DAY).parse(dateStr);
    }

}