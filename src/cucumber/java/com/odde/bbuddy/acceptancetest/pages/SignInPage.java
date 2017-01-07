package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;

@Component
@Scope("cucumber-glue")
public class SignInPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void signIn(String userName, String password) {
        driver.navigateTo(SIGNIN);
        driver.waitForTextPresent(labelTexts.head);
        driver.inputTextByName(userName, "username");
        driver.inputTextByName(password, "password");
        driver.clickByText(labelTexts.signin);
    }

}
