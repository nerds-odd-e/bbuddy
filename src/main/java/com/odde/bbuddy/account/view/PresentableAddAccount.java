package com.odde.bbuddy.account.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_ADD;

@Component
@RequestScope
public class PresentableAddAccount extends ModelAndView {

    public PresentableAddAccount() {
        setViewName(ACCOUNTS_ADD);
    }
}
