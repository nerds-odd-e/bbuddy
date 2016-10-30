package com.odde.bbuddy.budget.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "monthly_budgets")
@Getter
@Setter
@NoArgsConstructor
public class MonthlyBudget {

    @Builder
    public MonthlyBudget(LocalDate month, Integer budget) {
        this.month = month;
        this.budget = budget;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @DateTimeFormat(pattern = DAY)
    private LocalDate month;

    @NotNull
    private Integer budget;

    private long dayCount() {
        return DAYS.between(startOfMonth(), startOfMonth().plusMonths(1));
    }

    private LocalDate startOfMonth() {
        return month.withDayOfMonth(1);
    }

    private long dailyBudget() {
        return budget / dayCount();
    }

    private LocalDate endOfMonth() {
        return month.withDayOfMonth(month.lengthOfMonth());
    }

    public long overlappingBudget(Period period) {
        return dailyBudget() * period.overlappingDayCount(new Period(startOfMonth(), endOfMonth()));
    }
}
