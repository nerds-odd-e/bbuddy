package com.odde.bbuddy.budget;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "monthly_budgets")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private Date month;

    @NonNull
    private Integer budget;
}
