package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.data.account.EditableAccount;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNT_ADD;

@Component
@Scope("cucumber-glue")
public class AddAccountPage {

    @Autowired
    UiDriver driver;

    public void add(EditableAccount account) {
        driver.navigateTo(ACCOUNT_ADD);
        setName(account.getName());
        setBalanceBroughtForward(account.getBalanceBroughtForward());
//        driver.findElementByName("name").submit();
    }

    public void setBalanceBroughtForward(String balanceBroughtForward) {
        driver.findElementByName("balanceBroughtForward").sendKeys(balanceBroughtForward);
    }

    public void setName(String name) {
        driver.findElementByName("name").sendKeys(name);
    }
}
