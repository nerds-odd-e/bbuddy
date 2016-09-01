package com.odde.bbuddy.acceptancetest.steps;

import org.junit.Assert;

import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AssertionHelper {
    public static void assertListDeepEquals(List expected, List actual) {
        Assert.assertEquals(expected.size(), actual.size());
        assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }
}