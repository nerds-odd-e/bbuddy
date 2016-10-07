package com.odde.bbuddy.budget.builder;

import com.odde.bbuddy.budget.domain.MonthlyBudget;

import static com.odde.bbuddy.budget.domain.MonthlyBudget.builder;
import static com.odde.bbuddy.common.Formats.parseMonth;

public class MonthlyBudgetBuilder {

    public static MonthlyBudget.MonthlyBudgetBuilder defaultMonthlyBudget() {
        return builder()
                .month(parseMonth("2015-07")).budget(200);
    }

    public static MonthlyBudget monthlyBudget(String month, int budget) {
        return builder().month(parseMonth(month)).budget(budget).build();
    }

}
