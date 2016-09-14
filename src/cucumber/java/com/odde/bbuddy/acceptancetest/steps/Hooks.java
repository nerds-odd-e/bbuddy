package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.Application;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
import com.odde.bbuddy.acceptancetest.data.budget.MonthlyBudgetRepoForTest;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.pages.SignInPage;
import com.odde.bbuddy.user.domain.User;
import com.odde.bbuddy.user.repo.UserRepo;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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

    @Autowired
    SignInPage signInPage;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MonthlyBudgetRepoForTest monthlyBudgetRepo;

    @Autowired
    TransactionRepoForTest transactionRepo;

    @Before("@user")
    public void signIn(){
        userRepo.save(new User("user", "password"));
        signInPage.signIn("user", "password");
    }

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }

    @Before("@monthlyBudget")
    @After("@monthlyBudget")
    public void cleanUpMonthlyBudget() {
        monthlyBudgetRepo.deleteAll();
    }

    @After("@user")
    public void cleanUpUser() {
        userRepo.deleteAll();
    }

    @Before("@transaction")
    @After("@transaction")
    public void cleanUpTransaction() {
        transactionRepo.deleteAll();
    }

}
