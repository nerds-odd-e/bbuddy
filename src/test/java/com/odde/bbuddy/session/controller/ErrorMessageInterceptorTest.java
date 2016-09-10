package com.odde.bbuddy.session.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class ErrorMessageInterceptorTest {

    HttpServletRequest notUsedRequest = mock(HttpServletRequest.class);
    HttpServletResponse notUsedResponse = mock(HttpServletResponse.class);
    Object notUsedHandler = new Object();
    ModelAndView stubModelAndView = mock(ModelAndView.class);
    ErrorMessageInterceptor interceptor = new ErrorMessageInterceptor();
    ModelMap mockModelMap = mock(ModelMap.class);

    @Before
    public void givenModelMap() {
        when(stubModelAndView.getModelMap()).thenReturn(mockModelMap);
    }

    @Test
    public void will_show_error_message_when_has_one_field_error() throws Exception {
        givenFieldErrors(new FieldError("notUsedObjectName", "field", "error message"));

        postHandle();

        verify(mockModelMap).addAttribute("error.field", "error message");
    }

    @Test
    public void will_show_error_message_when_has_two_field_errors() throws Exception {
        givenFieldErrors(
                new FieldError("notUsedObjectName", "field", "error message"),
                new FieldError("notUsedObjectName1", "field1", "another error message"));

        postHandle();

        verify(mockModelMap).addAttribute("error.field", "error message");
        verify(mockModelMap).addAttribute("error.field1", "another error message");
    }

    private void postHandle() throws Exception {
        interceptor.postHandle(notUsedRequest, notUsedResponse, notUsedHandler, stubModelAndView);
    }

    private void givenFieldErrors(FieldError... errors) {
        HashMap<String, Object> modelMap = new HashMap<>();
        modelMap.put(BindingResult.MODEL_KEY_PREFIX + "monthlyBudget", stubBindingResult(errors));
        when(stubModelAndView.getModel()).thenReturn(modelMap);
    }

    private BindingResult stubBindingResult(FieldError[] errors) {
        BindingResult stubBindingResult = mock(BindingResult.class);
        when(stubBindingResult.getFieldErrors()).thenReturn(asList(errors));
        return stubBindingResult;
    }

}
