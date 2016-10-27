package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.data.account.EditableAccount;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_ADD;

@Component
@Scope("cucumber-glue")
public class AddAccountPage {

    @Autowired
    UiDriver driver;

    public void add(EditableAccount account) {
        driver.navigateTo(ACCOUNTS_ADD);
        setBalanceBroughtForward(account.getBalanceBroughtForward());
        setNameAndSubmit(account.getName());
    }

    public void setBalanceBroughtForward(String balanceBroughtForward) {
        driver.findElementByName("balanceBroughtForward").sendKeys(balanceBroughtForward);
    }

    public void setNameAndSubmit(String name) {
        UiElement element = driver.findElementByName("name");
        element.sendKeys(name);
        element.submit();
    }
}
