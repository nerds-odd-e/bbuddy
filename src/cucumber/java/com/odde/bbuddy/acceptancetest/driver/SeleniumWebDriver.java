package com.odde.bbuddy.acceptancetest.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class SeleniumWebDriver implements UiDriver {

    private final WebDriver webDriver = new FirefoxDriver();

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }
}
