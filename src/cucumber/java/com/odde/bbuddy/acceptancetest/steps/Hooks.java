package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.Application;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import com.odde.bbuddy.user.UserRepo;
import cucumber.api.java.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
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
