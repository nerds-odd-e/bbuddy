package com.odde.bbuddy.transaction.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_ADD;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.values;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableAddTransaction extends ModelAndView {

    public PresentableAddTransaction() {
        addObject("types", values());
        setViewName(TRANSACTION_ADD);
    }

}
