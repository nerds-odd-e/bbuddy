package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.Application;
import com.odde.bbuddy.acceptancetest.data.ApplicationConfigurations;
import com.odde.bbuddy.acceptancetest.data.account.AccountRepoForTest;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
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
    TransactionRepoForTest transactionRepo;

    @Autowired
    AccountRepoForTest accountRepo;

    @Autowired
    ApplicationConfigurations applicationConfigurations;

    @Before("@user")
    public void signIn(){
        userRepo.save(new User("user", "password"));
        signInPage.signIn("user", "password");
    }

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }

    @After("@user,@deleteUser")
    public void cleanUpUser() {
        userRepo.deleteAll();
    }

    @Before("@transaction")
    @After("@transaction")
    public void cleanUpTransaction() {
        transactionRepo.deleteAll();
    }

    @Before("@account")
    @After("@account")
    public void cleanUpAccount() {
        accountRepo.deleteAll();
    }

    @After("@restoreApplicationConfiguration")
    public void restoreApplicationConfiguration() {
        applicationConfigurations.restore();
    }

}
