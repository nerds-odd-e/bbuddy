package com.odde.bbuddy.budget.builder;

import com.odde.bbuddy.budget.view.PresentableMonthlyBudgetAmount;

import static com.odde.bbuddy.budget.view.PresentableMonthlyBudgetAmount.builder;

public class PresentableMonthlyBudgetAmountBuilder {

    public static PresentableMonthlyBudgetAmount.PresentableMonthlyBudgetAmountBuilder defaultPresentableMonthlyBudgetAmount() {
        return builder().message("whatever message");
    }
}
