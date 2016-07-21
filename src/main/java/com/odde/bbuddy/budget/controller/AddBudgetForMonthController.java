package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AddBudgetForMonthController {

    private final MonthlyBudgetPlanner planner;

    @Autowired
    public AddBudgetForMonthController(MonthlyBudgetPlanner planner) {
        this.planner = planner;
    }

    @RequestMapping("/confirm_add_budget_for_month")
    public String confirm(
            @RequestParam(name = "month") @DateTimeFormat(pattern = "yyyy-MM") Date monthDate,
            @RequestParam(name = "budget") int budget, Model model) {
        planner.addMonthlyBudget(
                new MonthlyBudget(monthDate, budget),
                () -> model.addAttribute("message", "Successfully add budget for month"), () -> {});
        return "add_budget_for_month";
    }
}
