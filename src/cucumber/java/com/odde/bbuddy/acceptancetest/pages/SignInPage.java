package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class SignInPage {

    @Autowired
    UiDriver driver;

    public void signIn(String userName, String password) {
        driver.navigateTo("/signin");
        setPassword(password);
        setUserNameAndSubmit(userName);
    }

    private void setUserNameAndSubmit(String userName) {
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
        userNameTextBox.submit();
    }

    private void setPassword(String password) {
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
    }

}
