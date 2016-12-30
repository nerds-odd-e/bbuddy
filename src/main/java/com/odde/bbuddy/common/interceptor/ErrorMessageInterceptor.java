package com.odde.bbuddy.common.interceptor;

import com.odde.bbuddy.common.view.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

public class ErrorMessageInterceptor implements HandlerInterceptor {

    private final ErrorMessage errorMessage;

    @Autowired
    public ErrorMessageInterceptor(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null){
            return;
        }
        allFieldErrors(modelAndView).forEach(this::display);
    }

    private List<FieldError> allFieldErrors(ModelAndView modelAndView) {
        return modelAndView.getModelMap().entrySet().stream()
                .filter(this::hasFieldError)
                .flatMap(this::fieldErrors)
                .collect(toList());
    }

    private boolean hasFieldError(Entry<String, Object> entry) {
        return entry.getKey().startsWith(MODEL_KEY_PREFIX);
    }

    private Stream<FieldError> fieldErrors(Entry<String, Object> entry) {
        return ((BindingResult)entry.getValue()).getFieldErrors().stream();
    }

    private void display(FieldError fieldError) {
        errorMessage.display(fieldError);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //no implementation needed
    }
}
