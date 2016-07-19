package com.odde.bbuddy.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonthlyBudgetPlanner {
    private final BudgetCategoryImpl budgetCategory;

    @Autowired
    public MonthlyBudgetPlanner(BudgetCategoryImpl budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public long getAmount(Date startDate, Date endDate) {
        return budgetCategory.getAmount(startDate, endDate);
    }
}
