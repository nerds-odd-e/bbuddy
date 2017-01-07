package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS;

@Component
@Scope("cucumber-glue")
public class ShowAllTransactionsPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void show() {
        driver.navigateTo(TRANSACTIONS);
        driver.waitForTextPresent(labelTexts.transactionsTitle);
    }

    public void navigateToPage(int pageNumber) {
        driver.navigateToWithParams(TRANSACTIONS, paramsWithPage(pageNumber));
        driver.waitForTextPresent(labelTexts.transactionsTitle);
    }

    private Params paramsWithPage(int pageNumber) {
        Params params = new Params();
        params.add("page", String.valueOf(pageNumber));
        return params;
    }
}
