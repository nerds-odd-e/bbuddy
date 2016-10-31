package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import com.odde.bbuddy.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;

@Service
public class MonthlyBudgetPlanner {
    private final BudgetCategoryImpl budgetCategory;
    private final MonthlyBudgetRepo monthlyBudgetRepo;

    @Autowired
    public MonthlyBudgetPlanner(BudgetCategoryImpl budgetCategory, MonthlyBudgetRepo monthlyBudgetRepo) {
        this.budgetCategory = budgetCategory;
        this.monthlyBudgetRepo = monthlyBudgetRepo;
    }

    public long getAmount(LocalDate startDate, LocalDate endDate) {
        allPlannedBudgets().forEach(this::setAmount);

        return getTotalAmount(localDateOf(startDate), localDateOf(endDate));
    }

    private long getTotalAmount(Date startDate, Date endDate) {
        return budgetCategory.getAmount(startDate, endDate);
    }

    private void setAmount(MonthlyBudget monthlyBudget) {
        budgetCategory.setAmount(
                localDateOf(monthlyBudget.getMonth()),
                monthlyBudget.getBudget());
    }

    private Date localDateOf(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Iterable<MonthlyBudget> allPlannedBudgets() {
        return monthlyBudgetRepo.findAll();
    }

    public PostActions addMonthlyBudget(MonthlyBudget monthlyBudget) {
        try {
            saveMonthlyBudget(monthlyBudget);
            return success();
        } catch (IllegalArgumentException e) {
            return failed();
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
