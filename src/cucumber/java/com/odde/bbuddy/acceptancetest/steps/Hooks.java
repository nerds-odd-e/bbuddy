package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {

    @Autowired
    UiDriver uiDriver;

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }

}
