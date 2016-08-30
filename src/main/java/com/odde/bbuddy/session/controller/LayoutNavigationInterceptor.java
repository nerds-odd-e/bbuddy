package com.odde.bbuddy.session.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.odde.bbuddy.Urls.MONTHLYBUDGET_ADD;
import static com.odde.bbuddy.Urls.TRANSACTION_ADD;

public class LayoutNavigationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("monthlyBudgetAddUrl", MONTHLYBUDGET_ADD);
        modelAndView.addObject("transactionAddUrl", TRANSACTION_ADD);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
