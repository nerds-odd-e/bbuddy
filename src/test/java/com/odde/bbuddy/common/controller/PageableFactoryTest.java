package com.odde.bbuddy.common.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.springframework.data.domain.Pageable;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageableFactoryTest {

    private final int page = 1;
    private final int perPageLimit = 10;
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);

    @Test
    public void create_pageable_with_default_limit_per_page() {
        given_page_number_is(page);

        Pageable pageable = pageableFactory(perPageLimit).create();

        assertPageSizeAndNumberEquals(perPageLimit, page, pageable);
    }

    @Test
    public void create_pageable_with_default_page_number() {
        given_no_page_number_in_request();

        Pageable pageable = pageableFactory(perPageLimit).create();

        assertPageSizeAndNumberEquals(perPageLimit, 0, pageable);
    }

    private void given_no_page_number_in_request() {
        when(mockRequest.getParameter("page")).thenReturn(null);
    }

    private void given_page_number_is(int page) {
        when(mockRequest.getParameter("page")).thenReturn(valueOf(page));
    }

    private PageableFactory pageableFactory(int perPageLimit) {
        return new PageableFactory(mockRequest, perPageLimit);
    }

    private void assertPageSizeAndNumberEquals(int perPageLimit, int page, Pageable pageable) {
        assertThat(pageable.getPageSize()).isEqualTo(perPageLimit);
        assertThat(pageable.getPageNumber()).isEqualTo(page);
    }

}