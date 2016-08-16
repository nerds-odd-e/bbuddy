package com.odde.bbuddy.acceptancetest.pages;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MonthlyBudgetAmountPage extends CommonPage {

    public void open(String startDate, String endDate) {
        driver.navigateTo("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
    }

}
