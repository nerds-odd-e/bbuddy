package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import com.odde.bbuddy.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;

@Service
public class MonthlyBudgets {
    private final MonthlyBudgetRepo monthlyBudgetRepo;

    @Autowired
    public MonthlyBudgets(MonthlyBudgetRepo monthlyBudgetRepo) {
        this.monthlyBudgetRepo = monthlyBudgetRepo;
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

    public void searchAmountOfPeriod(Consumer<Integer> consumer, Period period) {
        consumer.accept(37);
    }
}
