package com.odde.bbuddy.budget;

import lombok.*;

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
    @Setter
    private long id;

    @NonNull
    private Date month;

    @NonNull
    @Setter
    private Integer budget;
}
