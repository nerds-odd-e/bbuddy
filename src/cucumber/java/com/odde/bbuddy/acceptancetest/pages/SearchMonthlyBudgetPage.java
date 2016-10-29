package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGETS_SEARCH;

@Component
@Scope("cucumber-glue")
public class SearchMonthlyBudgetPage {

    @Autowired
    UiDriver driver;

    public void searchAmountOfPeriod(String startDate, String endDate) {
        driver.navigateTo(MONTHLYBUDGETS_SEARCH);
        setEndDate(endDate);
        setStartDateAndSubmit(startDate);
    }

    private void setStartDateAndSubmit(String startDate) {
        UiElement element = driver.findElementByName("startDate");
        element.sendKeys(startDate);
        element.submit();
    }

    private void setEndDate(String endDate) {
        driver.findElementByName("endDate").sendKeys(endDate);
    }
}
