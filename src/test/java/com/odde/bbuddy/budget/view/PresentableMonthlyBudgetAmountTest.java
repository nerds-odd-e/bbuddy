package com.odde.bbuddy.budget.view;

import org.junit.Test;

import static com.odde.bbuddy.budget.builder.PresentableMonthlyBudgetAmountBuilder.defaultPresentableMonthlyBudgetAmount;
import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class PresentableMonthlyBudgetAmountTest {

    private final long total = 100;
    PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount;

    @Test
    public void should_pass_amount_message_to_page() {
        presentableMonthlyBudgetAmount = defaultPresentableMonthlyBudgetAmount()
                .message("Amount is %s").build();

        display();

        assertThat(presentableMonthlyBudgetAmount.getModel().get("amount")).isEqualTo("Amount is 100");
    }

    @Test
    public void should_go_to_total_amount_view() {
        presentableMonthlyBudgetAmount = defaultPresentableMonthlyBudgetAmount().build();

        assertThat(presentableMonthlyBudgetAmount.getViewName()).isEqualTo(MONTHLYBUDGET_TOTALAMOUNT);
    }

    private void display() {
        presentableMonthlyBudgetAmount.display(total);
    }

}
