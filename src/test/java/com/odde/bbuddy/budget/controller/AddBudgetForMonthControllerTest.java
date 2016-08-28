package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddBudgetForMonthControllerTest {

    private static final int SUCCESS = 1;
    private static final int FAIL = 2;
    MonthlyBudgetPlanner stubPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(stubPlanner);
    Model mockModel = mock(Model.class);
    Date monthDate = parseDate("2016-07-01");

    @Test
    public void go_to_add_budget_for_month_page() throws ParseException {
        assertEquals("add_budget_for_month", controller.confirm(monthDate, 100, mockModel));
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() throws ParseException {
        given_add_monthly_budget_will(SUCCESS);

        controller.confirm(monthDate, 100, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add budget for month");
    }

    @Test
    public void return_add_fail_message_to_page_when_add_budget_for_month_failed() {
        given_add_monthly_budget_will(FAIL);

        controller.confirm(monthDate, 100, mockModel);

        verify(mockModel).addAttribute("message", "Add budget for month failed");
    }

    private void given_add_monthly_budget_will(int i) {
        doAnswer(invocation -> {
            Runnable afterFail = (Runnable) invocation.getArguments()[i];
            afterFail.run();
            return null;
        }).when(stubPlanner).addMonthlyBudget(any(MonthlyBudget.class), any(Runnable.class), any(Runnable.class));
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

    public AddBudgetForMonthControllerTest() throws ParseException {
    }

}
