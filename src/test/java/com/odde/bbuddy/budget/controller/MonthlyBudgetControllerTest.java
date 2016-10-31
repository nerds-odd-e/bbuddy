package com.odde.bbuddy.budget.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import com.odde.bbuddy.budget.view.PresentableAddMonthlyBudget;
import com.odde.bbuddy.budget.view.PresentableMonthlyBudgetAmount;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

import static com.odde.bbuddy.budget.builder.MonthlyBudgetBuilder.defaultMonthlyBudget;
import static com.odde.bbuddy.budget.builder.PresentableMonthlyBudgetAmountBuilder.defaultPresentableMonthlyBudgetAmount;
import static com.odde.bbuddy.common.Formats.parseDayToLocalDate;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class MonthlyBudgetControllerTest {

    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    View mockView = mock(View.class);
    PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount = spy(defaultPresentableMonthlyBudgetAmount().build());
    MonthlyBudgetController controller = new MonthlyBudgetController(
            mockPlanner,
            presentableMonthlyBudgetAmount,
            new PresentableAddMonthlyBudget(),
            mockView);
    BindingResult stubBindingResult = mock(BindingResult.class);
    MonthlyBudget monthlyBudget = defaultMonthlyBudget().build();

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addMonthlyBudget()).isInstanceOf(PresentableAddMonthlyBudget.class);
        }

    }

    public class SubmitAdd {

        @Before
        public void given_add_monthly_budget_will_success() {
            given_add_monthly_budget_will(success());
        }

        @Before
        public void given_messages() {
            controller.successMessage = "a success message";
            controller.failedMessage = "a failed message";
        }

        @Test
        public void should_go_to_view() {
            assertThat(submitAddMonthlyBudget(monthlyBudget)).isInstanceOf(PresentableAddMonthlyBudget.class);
        }

        @Test
        public void should_add_monthly_budget() {
            submitAddMonthlyBudget(monthlyBudget);

            verify(mockPlanner).addMonthlyBudget(monthlyBudget);
        }

        public class Success {

            @Test
            public void should_display_success_message() {
                given_add_monthly_budget_will(success());

                submitAddMonthlyBudget(monthlyBudget);

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }

        }

        public class Failed {

            @Test
            public void should_display_failed_message() {
                given_add_monthly_budget_will(failed());

                submitAddMonthlyBudget(monthlyBudget);

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }

        }

        private void given_add_monthly_budget_will(PostActions postActions) {
            when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
        }

    }

    public class Valid {

        MonthlyBudget invalidMonthlyBudget = new MonthlyBudget(null, null);

        @Before
        public void given_has_some_field_error() {
            given_has_field_error(true);
        }

        @Test
        public void should_not_add_monthly_budget() {
            submitAddMonthlyBudget(invalidMonthlyBudget);

            verify(mockPlanner, never()).addMonthlyBudget(invalidMonthlyBudget);
        }

        @Test
        public void should_display_view() {
            assertThat(submitAddMonthlyBudget(invalidMonthlyBudget)).isInstanceOf(PresentableAddMonthlyBudget.class);
        }

    }

    public class GetAmount {

        private final long total = 100L;
        LocalDate startDate = parseDayToLocalDate("2016-07-01");
        LocalDate endDate = parseDayToLocalDate("2016-07-10");

        @Test
        public void should_display_view() {
            assertThat(getAmount()).isInstanceOf(PresentableMonthlyBudgetAmount.class);
        }

        @Test
        public void should_get_amount_from_monthly_budget_planner() {
            getAmount();

            verify(mockPlanner).getAmount(startDate, endDate);
        }

        @Test
        public void should_pass_amount_to_page() {
            given_planner_will_return_total_as(total);

            getAmount();

            verify(presentableMonthlyBudgetAmount).display(total);
        }

        private void given_planner_will_return_total_as(long total) {
            when(mockPlanner.getAmount(startDate, endDate)).thenReturn(total);
        }

        private ModelAndView getAmount() {
            return controller.totalAmountOfMonthlyBudget(startDate, endDate);
        }

    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private ModelAndView submitAddMonthlyBudget(MonthlyBudget monthlyBudget) {
        return controller.submitAddMonthlyBudget(monthlyBudget, stubBindingResult);
    }

}
