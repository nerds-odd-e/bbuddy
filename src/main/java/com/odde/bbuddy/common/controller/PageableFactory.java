package com.odde.bbuddy.common.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PageableFactory {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final HttpServletRequest request;
    private final int perPageLimit;

    public PageableFactory(
            @Autowired HttpServletRequest request,
            @Value("${application.perPageLimit}") int perPageLimit) {
        this.request = request;
        this.perPageLimit = perPageLimit;
    }

    public Pageable create() {
        return new PageRequest(pageNumber(), perPageLimit);
    }

    private int pageNumber() {
        if(request.getParameter("page") == null)
            return DEFAULT_PAGE_NUMBER;

        return parseInt(request.getParameter("page"));
    }

}
