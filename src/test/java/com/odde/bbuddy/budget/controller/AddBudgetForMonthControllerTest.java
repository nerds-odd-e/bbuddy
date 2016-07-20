package com.odde.bbuddy.budget.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddBudgetForMonthControllerTest {

    @Test
    public void go_to_add_budget_for_month_page() {
        AddBudgetForMonthController controller = new AddBudgetForMonthController();

        assertEquals("add_budget_for_month", controller.confirm());
    }
}
