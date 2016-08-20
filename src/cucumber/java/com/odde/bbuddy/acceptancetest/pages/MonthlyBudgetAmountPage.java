package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.driver.Params;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MonthlyBudgetAmountPage {

    @Autowired
    UiDriver driver;

    public void open(String startDate, String endDate) {
        Params params = new Params();
        params.add("startDate", startDate);
        params.add("endDate", endDate);
        driver.navigateToWithParams("/get_amount", params);
    }

}
