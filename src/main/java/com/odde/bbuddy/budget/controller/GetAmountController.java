package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.MonthlyBudgetPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class GetAmountController {

    private final MonthlyBudgetPlanner planner;

    @Autowired
    public GetAmountController(MonthlyBudgetPlanner planner) {
        this.planner = planner;
    }

    @RequestMapping("/get_amount")
    public String getAmount(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        planner.getAmount(startDate, endDate);

        return "get_amount";
    }

}
