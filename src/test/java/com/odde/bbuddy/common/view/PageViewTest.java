package com.odde.bbuddy.common.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageViewTest {

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);

    @Test
    public void page_number() {
        given_page_is("5");

        PageView view = new PageView(mockRequest, "Current page is %s");

        assertThat(view.getModelMap().get("currentPage")).isEqualTo("Current page is 5");
    }

    private void given_page_is(String page) {
        when(mockRequest.getParameter("page")).thenReturn(page);
    }
}
