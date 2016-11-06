package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.common.formatter.Month;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "monthly_budgets")
@Getter
@Setter
@NoArgsConstructor
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Month
    private LocalDate month;
    @NotNull
    private Integer budget;

    @Builder
    public MonthlyBudget(LocalDate month, Integer budget) {
        this.month = month;
        this.budget = budget;
    }
}
