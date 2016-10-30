package com.odde.bbuddy.budget.builder;

import com.odde.bbuddy.budget.domain.MonthlyBudget;

import java.time.LocalDate;

import static com.odde.bbuddy.budget.domain.MonthlyBudget.builder;
import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.format.DateTimeFormatter.ofPattern;

public class MonthlyBudgetBuilder {

    public static MonthlyBudget.MonthlyBudgetBuilder defaultMonthlyBudget() {
        return builder()
                .month(monthDate("2015-07-01")).budget(200);
    }

    public static MonthlyBudget monthlyBudget(String month, int budget) {
        return builder().month(monthDate(month + "-01")).budget(budget).build();
    }

    private static LocalDate monthDate(String text) {
        return LocalDate.parse(text, ofPattern(DAY));
    }

}
