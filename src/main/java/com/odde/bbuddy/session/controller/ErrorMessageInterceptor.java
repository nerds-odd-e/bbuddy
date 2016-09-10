package com.odde.bbuddy.session.controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorMessageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (bindingResult(modelAndView) != null)
            bindingResult(modelAndView).getFieldErrors()
                    .forEach(fieldError -> setErrorMessage(modelAndView.getModelMap(), fieldError));
    }

    private BindingResult bindingResult(ModelAndView modelAndView) {
        return (BindingResult) modelAndView.getModel().get(BindingResult.MODEL_KEY_PREFIX + "monthlyBudget");
    }

    private void setErrorMessage(ModelMap model, FieldError fieldError) {
        model.addAttribute("error." + fieldError.getField(), fieldError.getDefaultMessage());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
