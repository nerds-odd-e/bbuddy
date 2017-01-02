package com.odde.bbuddy.acceptancetest.driver;

import com.odde.bbuddy.common.view.Params;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebDriver implements UiDriver {

    private static final int DEFAULT_TIME_OUT_IN_SECONDS = 10;
    private final WebDriver webDriver = new FirefoxDriver();

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        webDriver.get(url);
    }

    @Override
    public UiElement findElementByName(String name) {
        return new SeleniumWebElement(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return new SeleniumWebElement(webDriver.findElement(By.tagName(tag)));
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        webDriver.get(url + params.getQuery());
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return new SeleniumSelect(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementById(String id) {
        return new SeleniumWebElement(webDriver.findElement(By.id(id)));
    }

    @Override
    public void waitForTextPresent(String text) {
        new WebDriverWait(webDriver, DEFAULT_TIME_OUT_IN_SECONDS).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return findElementByTag("body").getText().contains(text);
            }
        });
    }

    @Override
    public UiElement findLinkByText(String text) {
        return new SeleniumWebElement(webDriver.findElement(By.linkText(text)));
    }
}
