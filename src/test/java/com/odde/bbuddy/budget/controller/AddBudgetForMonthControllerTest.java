package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import com.odde.bbuddy.common.PostActions;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;

import static com.odde.bbuddy.Urls.MONTHLYBUDGET_ADD;
import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.PostActionsFactory.failed;
import static com.odde.bbuddy.common.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AddBudgetForMonthControllerTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner);
    Model mockModel = mock(Model.class);
    private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

    @Test
    public void go_to_monthly_budget_add_page() {
        assertThat(controller.addMonthlyBudget()).isEqualTo(MONTHLYBUDGET_ADD);
    }

    @Test
    public void go_to_add_budget_for_month_page() throws ParseException {
        given_add_monthly_budget_will(success());

        assertThat(controller.submitAddMonthlyBudget(monthlyBudget, mockModel)).isEqualTo(MONTHLYBUDGET_ADD);
    }

    @Test
    public void add_monthly_budget() {
        given_add_monthly_budget_will(success());

        controller.submitAddMonthlyBudget(monthlyBudget, mockModel);

        verify(mockPlanner).addMonthlyBudget(monthlyBudget);
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() throws ParseException {
        given_add_monthly_budget_will(success());

        controller.submitAddMonthlyBudget(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add budget for month");
    }

    @Test
    public void return_add_fail_message_to_page_when_add_budget_for_month_failed() {
        given_add_monthly_budget_will(failed());

        controller.submitAddMonthlyBudget(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Add budget for month failed");
    }

    private void given_add_monthly_budget_will(PostActions postActions) {
        when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
    }

}
