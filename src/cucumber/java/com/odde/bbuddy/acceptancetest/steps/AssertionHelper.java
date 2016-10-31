package com.odde.bbuddy.acceptancetest.steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionHelper {

    public static void assertListDeepEquals(List expected, List actual) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyElementsOf(expected);
    }

}