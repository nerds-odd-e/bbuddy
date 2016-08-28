package com.odde.bbuddy.acceptancetest.steps;

import org.junit.Assert;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.List;

import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AssertionHelper {
    public static void assertListDeepEquals(List expected, List actual) {
        Assert.assertEquals(expected.size(), actual.size());
        ReflectionAssert.assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }
}