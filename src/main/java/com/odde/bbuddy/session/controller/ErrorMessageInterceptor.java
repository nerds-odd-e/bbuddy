package com.odde.bbuddy.session.controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ErrorMessageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //If apply forEach directly on flatMap, there will be a concurrent access issue. But, this issue can't be capture by current unit tests and only capture by the acceptance tests. More investigation should be put here later.
        allFieldErrors(modelAndView)
                .forEach(fieldError -> setErrorMessage(modelAndView.getModelMap(), fieldError));
    }

    private List<FieldError> allFieldErrors(ModelAndView modelAndView) {
        return modelAndView.getModelMap().entrySet().stream()
                .filter(this::hasFieldError)
                .flatMap(this::fieldErrors)
                .collect(toList());
    }

    private boolean hasFieldError(Map.Entry<String, Object> entry) {
        return entry.getKey().startsWith(BindingResult.MODEL_KEY_PREFIX);
    }

    private Stream<FieldError> fieldErrors(Map.Entry<String, Object> entry) {
        return ((BindingResult)entry.getValue()).getFieldErrors().stream();
    }

    private void setErrorMessage(ModelMap model, FieldError fieldError) {
        model.addAttribute("error." + fieldError.getField(), fieldError.getDefaultMessage());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
