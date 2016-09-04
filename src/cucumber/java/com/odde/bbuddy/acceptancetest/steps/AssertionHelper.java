package com.odde.bbuddy.acceptancetest.steps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AssertionHelper {
    public static void assertListDeepEquals(List expected, List actual) {
        assertThat(actual.size()).isEqualTo(expected.size());
        assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }
}