package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.common.view.Params;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTION;

@Component
@Scope("cucumber-glue")
public class ShowAllTransactionsPage {

    @Autowired
    UiDriver uiDriver;

    public void show() {
        uiDriver.navigateTo(TRANSACTION);
    }

    public void navigateToPage(int pageNumber) {
        uiDriver.navigateToWithParams(TRANSACTION, paramsWithPage(pageNumber));
    }

    private Params paramsWithPage(int pageNumber) {
        Params params = new Params();
        params.add("page", String.valueOf(pageNumber));
        return params;
    }
}
