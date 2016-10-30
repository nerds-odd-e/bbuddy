package com.odde.bbuddy.budget.domain;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.budget.repo.MonthlyBudgetRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import static com.odde.bbuddy.budget.builder.MonthlyBudgetBuilder.defaultMonthlyBudget;
import static com.odde.bbuddy.budget.builder.MonthlyBudgetBuilder.monthlyBudget;
import static com.odde.bbuddy.common.Formats.DAY;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class MonthlyBudgetsTest {

    MonthlyBudgetRepo mockRepo = mock(MonthlyBudgetRepo.class);
    MonthlyBudgets monthlyBudgets = new MonthlyBudgets(mockRepo);

    public class Add {

        MonthlyBudget monthlyBudget = defaultMonthlyBudget().build();

        Runnable afterSuccess = mock(Runnable.class);
        Runnable afterFail = mock(Runnable.class);

        private static final long MONTH_BUDGET_ID = 1L;

        @Test
        public void save_monthly_budget() {
            monthlyBudgets.addMonthlyBudget(monthlyBudget);

            assertSavedMonthlyBudgetEquals(monthlyBudget);
        }

        @Test
        public void after_success_is_called_if_save_successfully() {
            monthlyBudgets.addMonthlyBudget(monthlyBudget)
                    .success(afterSuccess)
                    .failed(afterFail);

            verify(afterSuccess).run();
            verify(afterFail, never()).run();
        }

        @Test
        public void after_fail_is_called_if_save_failed() {
            given_save_will_fail();

            monthlyBudgets.addMonthlyBudget(monthlyBudget)
                    .success(afterSuccess)
                    .failed(afterFail);

            verify(afterFail).run();
            verify(afterSuccess, never()).run();
        }

        @Test
        public void overwrite_monthly_budget_when_budget_has_been_set_for_that_month() {
            given_existing_monthly_budget_with_id(monthlyBudget("2016-07", 100), MONTH_BUDGET_ID);

            MonthlyBudget overwrittenMonthlyBudget = monthlyBudget("2016-07", 200);
            monthlyBudgets.addMonthlyBudget(overwrittenMonthlyBudget);

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

    public class GetAmountOfPeriod {

        Consumer mockConsumer = mock(Consumer.class);

        @Test
        public void no_planned_monthly_budget() {
            searchAmountOfPeriod("2016-06-01", "2016-06-30");

            assertAmountOfPeriodEquals(0);
        }

        @Test
        public void amount_of_period_which_exactly_the_month_with_budget_planned() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-06", 30));

            searchAmountOfPeriod("2016-06-01", "2016-06-30");

            assertAmountOfPeriodEquals(30);
        }
        
        @Test
        public void period_in_one_month_with_budget_planned() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-07", 31));

            searchAmountOfPeriod("2016-07-01", "2016-07-01");
            assertAmountOfPeriodEquals(1);
        }
        
        @Test
        public void period_overlap_with_month_and_start_date_not_in_month() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-06", 60));

            searchAmountOfPeriod("2016-05-15", "2016-06-15");

            assertAmountOfPeriodEquals(30);
        }
        
        @Test
        public void period_overlap_with_month_and_end_date_not_in_month() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-06", 60));

            searchAmountOfPeriod("2016-06-16", "2016-07-15");

            assertAmountOfPeriodEquals(30);
        }
        
        @Test
        public void period_overlap_with_two_months() {
            given_monthly_budget_planned_as(
                    monthlyBudget("2016-06", 60),
                    monthlyBudget("2016-07", 31));

            searchAmountOfPeriod("2016-06-16", "2016-07-15");

            assertAmountOfPeriodEquals(45);
        }

        private void searchAmountOfPeriod(String startDate, String endDate) {
            monthlyBudgets.searchAmountOfPeriod(mockConsumer, period(startDate, endDate));
        }

        private Period period(String startDate, String endDate) {
            Period period = new Period();
            period.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DAY)));
            period.setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DAY)));
            return period;
        }

        private void given_monthly_budget_planned_as(MonthlyBudget... monthlyBudget) {
            when(mockRepo.findAll()).thenReturn(asList(monthlyBudget));
        }

        private void assertAmountOfPeriodEquals(long amount) {
            verify(mockConsumer).accept(amount);
        }

    }

}
