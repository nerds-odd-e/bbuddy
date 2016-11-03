package com.odde.bbuddy.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.odde.bbuddy.common.controller.Urls.*;

public class LayoutNavigationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("monthlyBudgetsAddUrl", MONTHLYBUDGETS_ADD);
        modelAndView.addObject("transactionsUrl", TRANSACTIONS);
        modelAndView.addObject("accountsUrl", ACCOUNTS_ADD);
        modelAndView.addObject("signoutUrl", SIGNOUT);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
