package com.odde.bbuddy.budget.domain;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static com.odde.bbuddy.budget.builder.MonthlyBudgetBuilder.defaultMonthlyBudget;
import static com.odde.bbuddy.budget.builder.MonthlyBudgetBuilder.monthlyBudget;
import static com.odde.bbuddy.common.Formats.parseDay;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class MonthlyBudgetPlannerTest {

    MonthlyBudgetRepo mockRepo = mock(MonthlyBudgetRepo.class);
    BudgetCategoryImpl mockBudgetCategory = mock(BudgetCategoryImpl.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory, mockRepo);

    public class AddMonthlyBudget {

        MonthlyBudget monthlyBudget = defaultMonthlyBudget().build();

        Runnable afterSuccess = mock(Runnable.class);
        Runnable afterFail = mock(Runnable.class);

        private static final long MONTH_BUDGET_ID = 1L;

        @Test
        public void save_monthly_budget() {
            planner.addMonthlyBudget(monthlyBudget);

            assertSavedMonthlyBudgetEquals(monthlyBudget);
        }

        @Test
        public void after_success_is_called_if_save_successfully() {
            planner.addMonthlyBudget(monthlyBudget)
                    .success(afterSuccess)
                    .failed(afterFail);

            verify(afterSuccess).run();
            verify(afterFail, never()).run();
        }

        @Test
        public void after_fail_is_called_if_save_failed() {
            given_save_will_fail();

            planner.addMonthlyBudget(monthlyBudget)
                    .success(afterSuccess)
                    .failed(afterFail);

            verify(afterFail).run();
            verify(afterSuccess, never()).run();
        }

        @Test
        public void overwrite_monthly_budget_when_budget_has_been_set_for_that_month() {
            given_existing_monthly_budget_with_id(monthlyBudget("2016-07", 100), MONTH_BUDGET_ID);

            MonthlyBudget overwrittenMonthlyBudget = monthlyBudget("2016-07", 200);
            planner.addMonthlyBudget(overwrittenMonthlyBudget);

            MonthlyBudget savedMonthlyBudget = assertSavedMonthlyBudgetEquals(overwrittenMonthlyBudget);
            assertThat(savedMonthlyBudget.getId()).isEqualTo(MONTH_BUDGET_ID);
        }

        private void given_existing_monthly_budget_with_id(MonthlyBudget monthlyBudget, long id) {
            when(mockRepo.findByMonth(monthlyBudget.getMonth())).thenReturn(monthlyBudget);
            monthlyBudget.setId(id);
        }

        private void given_save_will_fail() {
            doThrow(IllegalArgumentException.class).when(mockRepo).save(any(MonthlyBudget.class));
        }

        private MonthlyBudget assertSavedMonthlyBudgetEquals(MonthlyBudget expectedMonthlyBudget) {
            MonthlyBudget savedMonthlyBudget = captureSavedMonthlyBudget();
            assertThat(savedMonthlyBudget).isEqualToIgnoringGivenFields(expectedMonthlyBudget, "id");
            return savedMonthlyBudget;
        }

        private MonthlyBudget captureSavedMonthlyBudget() {
            ArgumentCaptor<MonthlyBudget> captor = ArgumentCaptor.forClass(MonthlyBudget.class);
            verify(mockRepo).save(captor.capture());
            return captor.getValue();
        }

    }

    public class GetAmountOfMonthlyBudget {

        Date startDate = parseDay("2016-07-01");
        Date endDate = parseDay("2016-07-10");

        @Test
        public void get_amount_from_budget_category() {
            given_monthly_budget_planned_as();
            given_total_amount_is(100L);

            assertThat(planner.getAmount(startDate, endDate)).isEqualTo(100L);
        }

        @Test
        public void read_from_repo_and_set_amount() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-06", 30),
                    monthlyBudget("2016-07", 31));

            planner.getAmount(startDate, endDate);

            verify(mockBudgetCategory).setAmount(parseDay("2016-06-01"), 30);
            verify(mockBudgetCategory).setAmount(parseDay("2016-07-01"), 31);
        }

        private void given_monthly_budget_planned_as(MonthlyBudget... budget) {
            when(mockRepo.findAll()).thenReturn(asList(budget));
        }

        private void given_total_amount_is(long total) {
            when(mockBudgetCategory.getAmount(any(Date.class), any(Date.class))).thenReturn(total);
        }
    }

}
