package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_ADD;

@Component
@Scope("cucumber-glue")
public class AddMonthlyBudgetPage {

    @Autowired
    UiDriver driver;

    public void addMonthlyBudget(String month, String budget) {
        driver.navigateTo(MONTHLYBUDGET_ADD);
        setMonth(month);
        setBudgetAndSubmit(budget);
    }

    private void setBudgetAndSubmit(String budget) {
        UiElement budgetTextBox = driver.findElementByName("budget");
        budgetTextBox.sendKeys(budget);
        budgetTextBox.submit();
    }

    private void setMonth(String month) {
        driver.findElementByName("month").sendKeys(month);
    }
}
