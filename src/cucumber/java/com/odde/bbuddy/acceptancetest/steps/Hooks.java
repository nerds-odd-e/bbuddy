package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import com.odde.bbuddy.user.UserRepo;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {

    @Autowired
    UiDriver uiDriver;

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }

    @Autowired
    MonthlyBudgetRepo monthlyBudgetRepo;

    @After("@monthlyBudget")
    public void cleanUpMonthlyBudget() {
        monthlyBudgetRepo.deleteAll();
    }

    @Autowired
    UserRepo userRepo;

    @After("@user")
    public void cleanUpUser() {
        userRepo.deleteAll();
    }

}
