package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.Formats.parseMonth;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetAmountOfMonthlyBudgetPlannerTest {

    BudgetCategoryImpl mockBudgetCategory = mock(BudgetCategoryImpl.class);
    MonthlyBudgetRepo stubRepo = mock(MonthlyBudgetRepo.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory, stubRepo);

    Date startDate = parseDay("2016-07-01");
    Date endDate = parseDay("2016-07-10");

    @Test
    public void get_amount_from_budget_category() {
        given_monthly_budget_planned_as();
        given_total_amount_is(100L);

        assertThat(planner.getAmount(startDate, endDate)).isEqualTo(100L);
    }

    @Test
    public void read_from_repo_and_set_amount() {
        given_monthly_budget_planned_as(
                budget("2016-06", 30),
                budget("2016-07", 31));

        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).setAmount(parseDay("2016-06-01"), 30);
        verify(mockBudgetCategory).setAmount(parseDay("2016-07-01"), 31);
    }

    private void given_monthly_budget_planned_as(MonthlyBudget... budget) {
        when(stubRepo.findAll()).thenReturn(Arrays.asList(budget));
    }

    private MonthlyBudget budget(String month, int budget) {
        return new MonthlyBudget(parseMonth(month), budget);
    }

    private void given_total_amount_is(long total) {
        when(mockBudgetCategory.getAmount(any(Date.class), any(Date.class))).thenReturn(total);
    }

}
