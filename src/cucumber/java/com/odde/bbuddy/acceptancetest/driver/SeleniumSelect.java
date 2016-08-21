package com.odde.bbuddy.acceptancetest.driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumSelect implements UiSelect {
    private final WebElement element;

    public SeleniumSelect(WebElement element) {
        this.element = element;
    }

    @Override
    public void selectByVisibleText(String text) {
        new Select(element).selectByVisibleText(text);
    }
}
