package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.driver.UiSelect;
import com.odde.bbuddy.acceptancetest.steps.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddTransactionPage {

    @Autowired
    UiDriver driver;

    public void add(Transaction transaction) {
        driver.navigateTo("/add_transaction");
        UiSelect type = driver.findSelectByName("type");
        type.selectByVisibleText(transaction.getType());
    }
}
