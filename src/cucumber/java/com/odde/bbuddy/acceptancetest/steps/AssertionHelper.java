package com.odde.bbuddy.acceptancetest.steps;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionHelper {
    public static void assertListDeepEquals(List expected, List actual, String dateField) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("id", dateField)
                .usingComparatorForElementFieldsWithNames((Comparator<Date>) Date::compareTo, dateField)
                .containsExactlyElementsOf(expected);
    }
}