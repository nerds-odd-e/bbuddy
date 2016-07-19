package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.MonthlyBudgetPlanner;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetAmountControllerTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    GetAmountController controller = new GetAmountController(mockPlanner);

    Date startDate = parseDate("2016-07-01");
    Date endDate = parseDate("2016-07-10");

    @Test
    public void go_to_get_amount_page() throws ParseException {
        assertEquals("get_amount", controller.getAmount(startDate, endDate));
    }

    @Test
    public void get_amount_from_monthly_budget_planner() throws ParseException {
        controller.getAmount(startDate, endDate);

        verify(mockPlanner).getAmount(startDate, endDate);
    }

    public GetAmountControllerTest() throws ParseException {
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

}
