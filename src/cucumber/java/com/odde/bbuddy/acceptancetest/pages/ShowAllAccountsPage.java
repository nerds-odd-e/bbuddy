package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class ShowAllAccountsPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void show() {
        driver.waitForTextPresent(labelTexts.appTitle);
        driver.clickByText(labelTexts.accountsLink);
        driver.waitForTextPresent(labelTexts.accountsTitle);
    }
}
