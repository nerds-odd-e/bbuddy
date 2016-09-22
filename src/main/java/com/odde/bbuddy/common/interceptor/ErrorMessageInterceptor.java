package com.odde.bbuddy.common.interceptor;

import com.odde.bbuddy.common.view.ErrorMessage;
import org.springframework.context.MessageSource;
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
import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

public class ErrorMessageInterceptor implements HandlerInterceptor {

    private final MessageSource messageSource;

    public ErrorMessageInterceptor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
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
        return entry.getKey().startsWith(MODEL_KEY_PREFIX);
    }

    private Stream<FieldError> fieldErrors(Map.Entry<String, Object> entry) {
        return ((BindingResult)entry.getValue()).getFieldErrors().stream();
    }

    private void setErrorMessage(ModelMap model, FieldError fieldError) {
        new ErrorMessage(model, fieldError, messageSource);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
