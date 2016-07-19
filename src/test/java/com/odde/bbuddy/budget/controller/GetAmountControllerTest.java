package com.odde.bbuddy.budget.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetAmountControllerTest {

    @Test
    public void go_to_get_amount_page() {
        GetAmountController controller = new GetAmountController();

        assertEquals("get_amount", controller.getAmount());
    }
}
