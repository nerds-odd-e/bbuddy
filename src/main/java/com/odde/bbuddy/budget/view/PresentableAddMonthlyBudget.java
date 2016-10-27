package com.odde.bbuddy.budget.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGETS_ADD;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableAddMonthlyBudget extends ModelAndView {

    public PresentableAddMonthlyBudget() {
        setViewName(MONTHLYBUDGETS_ADD);
    }
}
