package com.odde.bbuddy.budget;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepo extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(Date monthDate);
    Iterable<MonthlyBudget> findAll();
    void save(MonthlyBudget monthlyBudget);
}
