package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAmountControllerTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner);

    Date startDate = parseDate("2016-07-01");
    Date endDate = parseDate("2016-07-10");
    Model mockModel = mock(Model.class);

    @Test
    public void go_to_get_amount_page() throws ParseException {
        assertEquals("get_amount", controller.getAmount(startDate, endDate, mockModel));
    }

    @Test
    public void get_amount_from_monthly_budget_planner() throws ParseException {
        controller.getAmount(startDate, endDate, mockModel);

        verify(mockPlanner).getAmount(startDate, endDate);
    }

    @Test
    public void pass_amount_to_page() {
        when(mockPlanner.getAmount(startDate, endDate)).thenReturn(100L);

        controller.getAmount(startDate, endDate, mockModel);

        verify(mockModel).addAttribute("amount", 100L);
    }

    public GetAmountControllerTest() throws ParseException {
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }

}
