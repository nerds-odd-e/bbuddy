package com.odde.bbuddy.transaction.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS_ADD;
import static com.odde.bbuddy.transaction.repo.Transaction.Type.values;

@Component
@RequestScope
public class PresentableAddTransaction extends ModelAndView {

    public PresentableAddTransaction() {
        addObject("types", values());
        setViewName(TRANSACTIONS_ADD);
    }

}
