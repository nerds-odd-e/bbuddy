package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;
import static com.odde.bbuddy.common.Formats.MONTH;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GetAmountOfMonthlyBudgetPlannerTest {

    BudgetCategoryImpl mockBudgetCategory = mock(BudgetCategoryImpl.class);
    MonthlyBudgetRepo stubRepo = mock(MonthlyBudgetRepo.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory, stubRepo);

    Date startDate = parseDate("2016-07-01");
    Date endDate = parseDate("2016-07-10");

    @Test
    public void get_amount_from_budget_category() throws ParseException {
        given_monthly_budget_planned_as();
        given_total_amount_is(100L);

        assertEquals(100L, planner.getAmount(startDate, endDate));
    }

    @Test
    public void read_from_repo_and_set_amount() throws ParseException {
        given_monthly_budget_planned_as(
                budget("2016-06", 30),
                budget("2016-07", 31));

        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).setAmount(parseDate("2016-06-01"), 30);
        verify(mockBudgetCategory).setAmount(parseDate("2016-07-01"), 31);
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat(DAY).parse(source);
    }

    public GetAmountOfMonthlyBudgetPlannerTest() throws ParseException {
    }

    private void given_monthly_budget_planned_as(MonthlyBudget... budget) throws ParseException {
        when(stubRepo.findAll()).thenReturn(Arrays.asList(budget));
    }

    private MonthlyBudget budget(String month, int budget) throws ParseException {
        return new MonthlyBudget(new SimpleDateFormat(MONTH).parse(month), budget);
    }

    private void given_total_amount_is(long total) {
        when(mockBudgetCategory.getAmount(any(Date.class), any(Date.class))).thenReturn(total);
    }

}
