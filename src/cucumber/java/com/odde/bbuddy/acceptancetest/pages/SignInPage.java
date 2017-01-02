package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;
import static com.odde.bbuddy.common.view.MessageSources.LABEL_TEXT_FULL_NAME;

@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class SignInPage {

    @Autowired
    UiDriver driver;

    @Value("${signin.label.head}")
    String headMessage;

    public void signIn(String userName, String password) {
        driver.navigateTo(SIGNIN);
        driver.waitForTextPresent(headMessage);
        setPassword(password);
        setUserName(userName);
        driver.findElementById("signin").click();
    }

    private void setUserName(String userName) {
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
    }

    private void setPassword(String password) {
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
    }

}
