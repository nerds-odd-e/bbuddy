package com.odde.bbuddy.transaction.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.transaction.domain.Transaction.Type.values;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableAddTransaction {

    private final HttpServletRequest request;

    @Autowired
    public PresentableAddTransaction(HttpServletRequest request) {
        this.request = request;
    }

    public void display() {
        request.setAttribute("types", values());
    }
}
