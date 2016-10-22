package com.odde.bbuddy.common.page;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class CurrentPage {
    public static final int FIRST_PAGE = 1;
    private final HttpServletRequest request;

    @Autowired
    public CurrentPage(HttpServletRequest request) {
        this.request = request;
    }

    public int number() {
        if (pageParam() == null)
            return FIRST_PAGE;
        else
            return parsePageNumber();
    }

    private int parsePageNumber() {
        try {
            int page = parseInt(pageParam());

            if (page < 0)
                return FIRST_PAGE;
            else
                return page;
        } catch (NumberFormatException e) {
            return FIRST_PAGE;
        }
    }

    private String pageParam() {
        return request.getParameter("page");
    }
}
