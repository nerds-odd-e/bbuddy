package com.odde.bbuddy.budget.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.MONTH;
import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "monthly_budgets")
@Getter
@Setter
@NoArgsConstructor
public class MonthlyBudget {

    @Builder
    public MonthlyBudget(Date month, Integer budget) {
        this.month = month;
        this.budget = budget;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @DateTimeFormat(pattern = MONTH)
    private Date month;

    @NotNull
    private Integer budget;

    private long dayCount() {
        return DAYS.between(startOfMonth(), startOfMonth().plusMonths(1));
    }

    private LocalDate startOfMonth() {
        return month.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private long dailyBudget() {
        return getBudget() / dayCount();
    }

    private Period getPeriod() {
        Period period = new Period();
        period.setStartDate(month);
        period.setEndDate(endOfMonth());
        return period;
    }

    private Date endOfMonth() {
        return Date.from(startOfMonth().withDayOfMonth(startOfMonth().lengthOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public long overlappingBudget(Period period) {
        return dailyBudget() * period.overlappingDayCount(getPeriod());
    }
}
