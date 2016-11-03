package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS;

@Component
@Scope("cucumber-glue")
public class ShowAllAccountsPage {

    @Autowired
    UiDriver driver;

    public void show() {
        driver.navigateTo(ACCOUNTS);
    }
}
