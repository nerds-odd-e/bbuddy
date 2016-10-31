package com.odde.bbuddy.budget.repo;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
public interface MonthlyBudgetRepo extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(LocalDate monthDate);
    Iterable<MonthlyBudget> findAll();
    void save(MonthlyBudget monthlyBudget);
}
