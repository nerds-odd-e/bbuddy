package com.odde.bbuddy.budget;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MonthlyBudgetPlannerTest {

    @Test
    public void get_amount_from_budget_category() throws ParseException {
        BudgetCategoryImpl mockBudgetCategory = mock(BudgetCategoryImpl.class);
        MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory);

        Date startDate = parseDate("2016-07-01");
        Date endDate = parseDate("2016-07-10");
        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).getAmount(startDate, endDate);
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

}
