package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGETS_TOTALAMOUNT;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;

@Component
@Scope("cucumber-glue")
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class MonthlyBudgetAmountPage {

    @Autowired
    UiDriver driver;

    @Value("${monthlybudgets.totalamount.amount}")
    String totalAmountMessage;

    public void open(String startDate, String endDate) {
        Params params = new Params();
        params.add("startDate", startDate);
        params.add("endDate", endDate);
        driver.navigateToWithParams(MONTHLYBUDGETS_TOTALAMOUNT, params);
        driver.waitForTextPresent(format(totalAmountMessage, ""));
    }

}
