package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS;
import static com.odde.bbuddy.common.view.MessageSources.LABEL_TEXT_FULL_NAME;

@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class ShowAllTransactionsPage {

    @Autowired
    UiDriver driver;

    @Value("${transactions.index.label.title}")
    String title;

    public void show() {
        driver.navigateTo(TRANSACTIONS);
        driver.waitForTextPresent(title);
    }

    public void navigateToPage(int pageNumber) {
        driver.navigateToWithParams(TRANSACTIONS, paramsWithPage(pageNumber));
        driver.waitForTextPresent(title);
    }

    private Params paramsWithPage(int pageNumber) {
        Params params = new Params();
        params.add("page", String.valueOf(pageNumber));
        return params;
    }
}
