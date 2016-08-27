package com.odde.bbuddy.acceptancetest.data.budget;

import com.odde.bbuddy.budget.MonthlyBudget;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
@Scope("cucumber-glue")
public interface MonthlyBudgetRepoForTest extends Repository<MonthlyBudget, Long> {
    void deleteAll();
    void save(MonthlyBudget monthlyBudget);
    int count();
    Iterable<MonthlyBudget> findAll();
}
