package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import com.odde.bbuddy.budget.view.MonthlyBudgetAmount;
import com.odde.bbuddy.common.callback.PostActions;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.text.ParseException;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MonthlyBudgetControllerAddTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetAmount stubMonthlyBudgetAmount = mock(MonthlyBudgetAmount.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner, stubMonthlyBudgetAmount);
    Model mockModel = mock(Model.class);
    private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);
    BindingResult noErrorBindingResult = mock(BindingResult.class);

    @Test
    public void go_to_monthly_budget_add_page() {
        assertThat(controller.addMonthlyBudget()).isEqualTo(MONTHLYBUDGET_ADD);
    }

    @Test
    public void go_to_add_budget_for_month_page() throws ParseException {
        given_add_monthly_budget_will(success());

        assertThat(submitMonthlyBudgetAdd()).isEqualTo(MONTHLYBUDGET_ADD);
    }

    @Test
    public void add_monthly_budget() {
        given_add_monthly_budget_will(success());

        submitMonthlyBudgetAdd();

        verify(mockPlanner).addMonthlyBudget(monthlyBudget);
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() throws ParseException {
        given_add_monthly_budget_will(success());
        controller.successMessage = "a success message";

        submitMonthlyBudgetAdd();

        verify(mockModel).addAttribute("message", "a success message");
    }

    @Test
    public void return_add_fail_message_to_page_when_add_budget_for_month_failed() {
        given_add_monthly_budget_will(failed());
        controller.failedMessage = "a failed message";

        submitMonthlyBudgetAdd();

        verify(mockModel).addAttribute("message", "a failed message");
    }

    private String submitMonthlyBudgetAdd() {
        return controller.submitAddMonthlyBudget(monthlyBudget, noErrorBindingResult, mockModel);
    }

    private void given_add_monthly_budget_will(PostActions postActions) {
        when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
    }

}
