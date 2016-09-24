package com.odde.bbuddy.budget.view;

import com.odde.bbuddy.common.view.Model;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentableMonthlyBudgetAmountTest {

    @Test
    public void should_pass_amount_message_to_page() {
        Model mockModel = mock(Model.class);
        PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount = new PresentableMonthlyBudgetAmount(mockModel);
        presentableMonthlyBudgetAmount.message = "Amount is %s";

        presentableMonthlyBudgetAmount.display(100);

        verify(mockModel).addAttribute("amount", "Amount is 100");
    }
}
