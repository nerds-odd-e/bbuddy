package com.odde.bbuddy.budget.view;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class PresentableMonthlyBudgetAmountTest {

    private final int total = 100;
    PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount;

    @Test
    public void should_pass_amount_message_to_page() {
        presentableMonthlyBudgetAmount = new PresentableMonthlyBudgetAmount("Amount is %s");

        display();

        assertThat(presentableMonthlyBudgetAmount.getModel().get("amount")).isEqualTo("Amount is 100");
    }

    @Test
    public void should_go_to_total_amount_view() {
        presentableMonthlyBudgetAmount = new PresentableMonthlyBudgetAmount("whatever message");

        assertThat(display().getViewName()).isEqualTo(MONTHLYBUDGET_TOTALAMOUNT);
    }

    private ModelAndView display() {
        return presentableMonthlyBudgetAmount.display(total);
    }

}
