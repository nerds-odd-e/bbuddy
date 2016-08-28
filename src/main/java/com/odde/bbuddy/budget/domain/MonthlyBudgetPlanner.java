package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonthlyBudgetPlanner {
    private final BudgetCategoryImpl budgetCategory;
    private final MonthlyBudgetRepo monthlyBudgetRepo;

    @Autowired
    public MonthlyBudgetPlanner(BudgetCategoryImpl budgetCategory, MonthlyBudgetRepo monthlyBudgetRepo) {
        this.budgetCategory = budgetCategory;
        this.monthlyBudgetRepo = monthlyBudgetRepo;
    }

    public long getAmount(Date startDate, Date endDate) {
        allPlannedBudgets().forEach(this::setAmount);

        return getTotalAmount(startDate, endDate);
    }

    private long getTotalAmount(Date startDate, Date endDate) {
        return budgetCategory.getAmount(startDate, endDate);
    }

    private void setAmount(MonthlyBudget monthlyBudget) {
        budgetCategory.setAmount(monthlyBudget.getMonth(), monthlyBudget.getBudget());
    }

    private Iterable<MonthlyBudget> allPlannedBudgets() {
        return monthlyBudgetRepo.findAll();
    }

    public void addMonthlyBudget(MonthlyBudget monthlyBudget, Runnable afterSuccess, Runnable afterFail) {
        try {
            saveMonthlyBudget(monthlyBudget);
            afterSuccess.run();
        } catch (IllegalArgumentException e) {
            afterFail.run();
        }
    }

    private void saveMonthlyBudget(MonthlyBudget monthlyBudget) {
        MonthlyBudget existingBudget = monthlyBudgetRepo.findByMonth(monthlyBudget.getMonth());
        if (existingBudget != null) {
            existingBudget.setBudget(monthlyBudget.getBudget());
            monthlyBudgetRepo.save(existingBudget);
        } else
            monthlyBudgetRepo.save(monthlyBudget);
    }
}
