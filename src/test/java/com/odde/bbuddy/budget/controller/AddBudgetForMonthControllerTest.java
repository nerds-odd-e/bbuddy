package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetPlanner;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddBudgetForMonthControllerTest {

    MonthlyBudgetPlanner stubPlanner = mock(MonthlyBudgetPlanner.class);
    AddBudgetForMonthController controller = new AddBudgetForMonthController(stubPlanner);
    Model mockModel = mock(Model.class);
    Date monthDate = parseDate("2016-07-01");

    @Test
    public void go_to_add_budget_for_month_page() throws ParseException {
        assertEquals("add_budget_for_month", controller.confirm(monthDate, 100, mockModel));
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() throws ParseException {
        given_add_monthly_budget_will_succeed();

        controller.confirm(monthDate, 100, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add budget for month");
    }

    private void given_add_monthly_budget_will_succeed() {
        doAnswer(invocation -> {
            Runnable afterSuccess = (Runnable) invocation.getArguments()[1];
            afterSuccess.run();
            return null;
        }).when(stubPlanner).addMonthlyBudget(any(MonthlyBudget.class), any(Runnable.class));
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

    public AddBudgetForMonthControllerTest() throws ParseException {
    }

}
