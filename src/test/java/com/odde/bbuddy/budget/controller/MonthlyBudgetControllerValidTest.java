package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MonthlyBudgetControllerValidTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner);
    Model notUsedModel = mock(Model.class);
    MonthlyBudget invalidMonthlyBudget = new MonthlyBudget(null, null);
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void givenHasFieldErrors() {
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);
    }

    @Test
    public void will_not_add_monthly_budget_when_has_field_error() {
        submitAddMonthlyBudget();

        verify(mockPlanner, never()).addMonthlyBudget(invalidMonthlyBudget);
    }

    @Test
    public void will_go_to_add_monthly_budget_page_when_has_field_error() {
        assertThat(submitAddMonthlyBudget()).isEqualTo(MONTHLYBUDGET_ADD);
    }

    private String submitAddMonthlyBudget() {
        return controller.submitAddMonthlyBudget(invalidMonthlyBudget, stubBindingResult, notUsedModel);
    }

}
