package com.odde.bbuddy.acceptancetest.pages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.view.MessageSources.LABEL_TEXT_FULL_NAME;

@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class LabelTexts {

    @Value("${label.add}")
    public String add;

    @Value("${home.label.welcome}")
    public String welcome;

    @Value("${label.signout}")
    public String signout;

    @Value("${accounts.index.label.title}")
    public String accountsTitle;

    @Value("${label.appTitle}")
    public String appTitle;

    @Value("${label.accounts}")
    public String accountsLink;

    @Value("${transactions.index.label.title}")
    public String transactionsTitle;

    @Value("${signin.label.head}")
    public String head;

    @Value("${signin.label.submit}")
    public String signin;
}
