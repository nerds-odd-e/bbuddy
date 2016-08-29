package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import com.odde.bbuddy.common.PostActions;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.PostActionsFactory.failed;
import static com.odde.bbuddy.common.PostActionsFactory.success;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddBudgetForMonthControllerTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner);
    Model mockModel = mock(Model.class);
    private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

    @Test
    public void go_to_add_budget_for_month_page() throws ParseException {
        given_add_monthly_budget_will(success());

        assertEquals("add_budget_for_month", controller.confirm(monthlyBudget, mockModel));
    }

    @Test
    public void add_monthly_budget() {
        given_add_monthly_budget_will(success());

        controller.confirm(monthlyBudget, mockModel);

        verify(mockPlanner).addMonthlyBudget(monthlyBudget);
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() throws ParseException {
        given_add_monthly_budget_will(success());

        controller.confirm(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add budget for month");
    }

    @Test
    public void return_add_fail_message_to_page_when_add_budget_for_month_failed() {
        given_add_monthly_budget_will(failed());

        controller.confirm(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Add budget for month failed");
    }

    private void given_add_monthly_budget_will(PostActions postActions) {
        when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
    }

}
