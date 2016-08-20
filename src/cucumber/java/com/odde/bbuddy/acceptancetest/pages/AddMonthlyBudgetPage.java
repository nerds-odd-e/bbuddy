package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddMonthlyBudgetPage extends CommonPage {

    public void addMonthlyBudget(String month, int budget) {
        driver.navigateTo("/add_budget_for_month");
        UiElement monthTextBox = driver.findElementByName("month");
        monthTextBox.sendKeys(month);
        UiElement budgetTextBox = driver.findElementByName("budget");
        budgetTextBox.sendKeys(String.valueOf((Integer) budget));
        budgetTextBox.submit();
    }
}
