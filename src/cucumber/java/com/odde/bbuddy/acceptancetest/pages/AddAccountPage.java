package com.odde.bbuddy.acceptancetest.pages;

import com.odde.bbuddy.acceptancetest.data.account.EditableAccount;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_ADD;

@Component
@Scope("cucumber-glue")
public class AddAccountPage {

    @Autowired
    UiDriver driver;

    @Autowired
    LabelTexts labelTexts;

    public void add(EditableAccount account) {
        driver.navigateTo(ACCOUNTS_ADD);
        driver.inputTextByName(account.getBalanceBroughtForward(), "balanceBroughtForward");
        driver.inputTextByName(account.getName(), "name");
        driver.clickByText(labelTexts.add);
    }

}
