package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddMonthlyBudgetPage {

    @Autowired
    UiDriver driver;

    public void addMonthlyBudget(String month, int budget) {
        driver.navigateTo("/add_budget_for_month");
        setMonth(month);
        setBudgetAndSubmit(budget);
    }

    private void setBudgetAndSubmit(Integer budget) {
        UiElement budgetTextBox = driver.findElementByName("budget");
        budgetTextBox.sendKeys(String.valueOf(budget));
        budgetTextBox.submit();
    }

    private void setMonth(String month) {
        driver.findElementByName("month").sendKeys(month);
    }
}
