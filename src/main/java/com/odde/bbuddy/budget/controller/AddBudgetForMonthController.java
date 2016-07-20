package com.odde.bbuddy.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddBudgetForMonthController {

    @RequestMapping("/confirm_add_budget_for_month")
    public String confirm() {
        return "add_budget_for_month";
    }
}
