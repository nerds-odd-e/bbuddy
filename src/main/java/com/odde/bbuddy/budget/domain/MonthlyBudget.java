package com.odde.bbuddy.budget.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.MONTH;

@Entity
@Table(name = "monthly_budgets")
@Getter
@Setter
@NoArgsConstructor
public class MonthlyBudget {

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
}
