package com.odde.bbuddy.budget;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepo extends CrudRepository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(Date monthDate);
}
