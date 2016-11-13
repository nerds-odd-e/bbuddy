package com.odde.bbuddy.common.interceptor;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.odde.bbuddy.common.controller.Urls.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LayoutNavigationInterceptorTest {

    private final HttpServletRequest anyRequest = mock(HttpServletRequest.class);
    private final HttpServletResponse anyResponse = mock(HttpServletResponse.class);
    private final Object anyHandler = new Object();
    ModelAndView modelAndView = new ModelAndView();

    @Test
    public void left_panel_menu_urls() throws Exception {
        postHandle();

        assertThat(modelAndViewAttribute("transactionsUrl")).isEqualTo(TRANSACTIONS);
        assertThat(modelAndViewAttribute("accountsUrl")).isEqualTo(ACCOUNTS);
        assertThat(modelAndViewAttribute("signoutUrl")).isEqualTo(SIGNOUT);
        assertThat(modelAndViewAttribute("rootUrl")).isEqualTo(ROOT);
    }

    private Object modelAndViewAttribute(String url) {
        return modelAndView.getModelMap().get(url);
    }

    private void postHandle() throws Exception {
        new LayoutNavigationInterceptor().postHandle(anyRequest, anyResponse, anyHandler, modelAndView);
    }
}
