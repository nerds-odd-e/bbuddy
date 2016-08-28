package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.BudgetCategoryImpl;
import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetPlanner;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AddBudgetOfMonthlyBudgetPlannerTest {

    private static final long MONTH_BUDGET_ID = 1L;
    MonthlyBudgetRepo mockRepo = mock(MonthlyBudgetRepo.class);
    BudgetCategoryImpl stubBudgetCategory = mock(BudgetCategoryImpl.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(stubBudgetCategory, mockRepo);

    Date monthDate = parseDate("2016-07-01");
    private MonthlyBudget monthlyBudget = new MonthlyBudget(monthDate, 100);

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFail = mock(Runnable.class);
    Runnable whatever = () -> {};

    @Test
    public void save_monthly_budget() throws ParseException {
        planner.addMonthlyBudget(monthlyBudget, whatever, whatever);

        assertSavedMonthlyBudgetEquals(monthlyBudget);
    }

    @Test
    public void after_success_is_called_if_save_successfully() throws ParseException {
        planner.addMonthlyBudget(monthlyBudget, afterSuccess, afterFail);

        verify(afterSuccess).run();
        verify(afterFail, never()).run();
    }

    @Test
    public void after_fail_is_called_if_save_failed() {
        givenSaveWillFail();

        planner.addMonthlyBudget(monthlyBudget, afterSuccess, afterFail);

        verify(afterFail).run();
        verify(afterSuccess, never()).run();
    }

    @Test
    public void overwrite_monthly_budget_when_budget_has_been_set_for_that_month() {
        given_existing_monthly_budget_with_id(MONTH_BUDGET_ID);

        MonthlyBudget overwrittenMonthlyBudget = new MonthlyBudget(monthDate, 200);
        planner.addMonthlyBudget(overwrittenMonthlyBudget, whatever, whatever);

        MonthlyBudget savedMonthlyBudget = assertSavedMonthlyBudgetEquals(overwrittenMonthlyBudget);
        assertEquals(MONTH_BUDGET_ID, savedMonthlyBudget.getId());
    }

    private void given_existing_monthly_budget_with_id(long id) {
        when(mockRepo.findByMonth(monthDate)).thenReturn(monthlyBudget);
        monthlyBudget.setId(id);
    }

    private void givenSaveWillFail() {
        doThrow(IllegalArgumentException.class).when(mockRepo).save(any(MonthlyBudget.class));
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

    public AddBudgetOfMonthlyBudgetPlannerTest() throws ParseException {
    }

    private MonthlyBudget assertSavedMonthlyBudgetEquals(MonthlyBudget expectedMonthlyBudget) {
        MonthlyBudget savedMonthlyBudget = captureSavedMonthlyBudget();
        assertEquals(expectedMonthlyBudget.getMonth(), savedMonthlyBudget.getMonth());
        assertEquals(expectedMonthlyBudget.getBudget(), savedMonthlyBudget.getBudget());
        return savedMonthlyBudget;
    }

    private MonthlyBudget captureSavedMonthlyBudget() {
        ArgumentCaptor<MonthlyBudget> captor = ArgumentCaptor.forClass(MonthlyBudget.class);
        verify(mockRepo).save(captor.capture());
        return captor.getValue();
    }

}
