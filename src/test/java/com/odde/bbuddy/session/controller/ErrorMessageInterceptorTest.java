package com.odde.bbuddy.session.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

public class ErrorMessageInterceptorTest {

    HttpServletRequest notUsedRequest = mock(HttpServletRequest.class);
    HttpServletResponse notUsedResponse = mock(HttpServletResponse.class);
    Object notUsedHandler = new Object();
    ModelAndView stubModelAndView = mock(ModelAndView.class);
    ErrorMessageInterceptor interceptor = new ErrorMessageInterceptor();
    ModelMap modelMap = new ModelMap();

    @Before
    public void givenModelMap() {
        when(stubModelAndView.getModelMap()).thenReturn(modelMap);
        when(stubModelAndView.getModel()).thenReturn(modelMap);
    }

    @Test
    public void will_do_nothing_when_has_no_field_error() throws Exception {
        postHandle();

        assertThat(modelMap).isEmpty();
    }

    @Test
    public void will_show_error_message_when_has_one_field_error() throws Exception {
        givenFieldErrors(new FieldError("notUsedObjectName", "field", "error message"));

        postHandle();

        assertThat(modelMap).contains(new SimpleEntry("error.field", "error message"));
    }

    @Test
    public void will_show_error_message_when_has_two_field_errors() throws Exception {
        givenFieldErrors(
                new FieldError("notUsedObjectName", "field", "error message"),
                new FieldError("notUsedObjectName", "field1", "another error message"));

        postHandle();

        assertThat(modelMap).contains(
                new SimpleEntry("error.field", "error message"),
                new SimpleEntry("error.field1", "another error message"));
    }

    private void postHandle() throws Exception {
        interceptor.postHandle(notUsedRequest, notUsedResponse, notUsedHandler, stubModelAndView);
    }

    private void givenFieldErrors(FieldError... errors) {
        modelMap.addAttribute(MODEL_KEY_PREFIX + "anyObject", stubBindingResult(errors));
        add_another_attribute_so_that_concurrent_modification_exception_can_not_be_hidden();
    }

    private void add_another_attribute_so_that_concurrent_modification_exception_can_not_be_hidden() {
        modelMap.addAttribute("anotherAttribute", "anotherValue");
    }

    private BindingResult stubBindingResult(FieldError[] errors) {
        BindingResult stubBindingResult = mock(BindingResult.class);
        when(stubBindingResult.getFieldErrors()).thenReturn(asList(errors));
        return stubBindingResult;
    }

}
