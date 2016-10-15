package com.odde.bbuddy.common.view;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamsQueryTest {

    Params params = new Params();

    @Test
    public void empty_query_when_there_is_no_param() {
        assertThat(params.getQuery()).isEqualTo("");
    }

    @Test
    public void query_string_when_there_is_one_param() {
        params.add("name", "value");

        assertThat(params.getQuery()).isEqualTo("?name=value");
    }

    @Test
    public void query_string_when_there_are_two_params() {
        params.add("first", "firstValue");
        params.add("second", "secondValue");

        assertThat(params.getQuery()).isEqualTo("?first=firstValue&second=secondValue");
    }
}
