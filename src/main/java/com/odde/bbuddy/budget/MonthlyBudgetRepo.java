package com.odde.bbuddy.budget;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MonthlyBudgetRepo extends CrudRepository<MonthlyBudget, Long> {
}
