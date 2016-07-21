package com.odde.bbuddy.budget;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddBudgetOfMonthlyBudgetPlannerTest {

    MonthlyBudgetRepo mockRepo = mock(MonthlyBudgetRepo.class);
    BudgetCategoryImpl stubBudgetCategory = mock(BudgetCategoryImpl.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(stubBudgetCategory, mockRepo);

    Date monthDate = parseDate("2016-07-01");
    private MonthlyBudget monthlyBudget = new MonthlyBudget(monthDate, 100);

    @Test
    public void save_monthly_budget() throws ParseException {
        planner.addMonthlyBudget(monthlyBudget, ()->{});

        assertSavedMonthlyBudgetEquals(monthlyBudget);
    }

    @Test
    public void after_success_is_called_if_save_successfully() throws ParseException {
        Runnable afterSuccess = mock(Runnable.class);

        planner.addMonthlyBudget(monthlyBudget, afterSuccess);

        verify(afterSuccess).run();
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

    public AddBudgetOfMonthlyBudgetPlannerTest() throws ParseException {
    }

    private void assertSavedMonthlyBudgetEquals(MonthlyBudget expectedMonthlyBudget) {
        ArgumentCaptor<MonthlyBudget> captor = ArgumentCaptor.forClass(MonthlyBudget.class);
        verify(mockRepo).save(captor.capture());
        assertEquals(expectedMonthlyBudget.getMonth(), captor.getValue().getMonth());
        assertEquals(expectedMonthlyBudget.getBudget(), captor.getValue().getBudget());
    }

}
