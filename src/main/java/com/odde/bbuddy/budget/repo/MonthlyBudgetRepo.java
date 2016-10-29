package com.odde.bbuddy.budget.repo;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepo extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(Date monthDate);

    void save(MonthlyBudget monthlyBudget);
}
