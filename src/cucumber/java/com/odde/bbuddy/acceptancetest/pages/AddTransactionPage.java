package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.data.transaction.EditableTransaction;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS_ADD;

@Component
@Scope("cucumber-glue")
public class AddTransactionPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void add(EditableTransaction transaction) {
        driver.navigateTo(TRANSACTIONS_ADD);
        driver.selectOptionByTextAndElementName(transaction.getType(), "type");
        driver.inputTextByName(transaction.getDescription(), "description");
        driver.inputTextByName(transaction.getDate(), "date");
        driver.inputTextByName(transaction.getAmount(), "amount");
        driver.clickByText(labelTexts.add);
    }

}
