package com.odde.bbuddy.acceptancetest.driver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParamsQueryTest {

    Params params = new Params();

    @Test
    public void empty_query_when_there_is_no_param() {
        assertEquals("", params.getQuery());
    }

    @Test
    public void query_string_when_there_is_one_param() {
        params.add("name", "value");

        assertEquals("?name=value", params.getQuery());
    }
}
