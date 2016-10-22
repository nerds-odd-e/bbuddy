package com.odde.bbuddy.common.page;

import org.junit.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageableFactoryTest {

    private final int page = 1;
    private final int perPageLimit = 10;
    CurrentPage mockCurrentPage = mock(CurrentPage.class);

    @Test
    public void create_pageable() {
        given_page_number_is(page);

        Pageable pageable = pageableFactory(perPageLimit).create();

        assertPageSizeAndNumberEquals(perPageLimit, page - 1, pageable);
    }

    private void given_page_number_is(int page) {
        when(mockCurrentPage.number()).thenReturn(page);
    }

    private PageableFactory pageableFactory(int perPageLimit) {
        return new PageableFactory(perPageLimit, mockCurrentPage);
    }

    private void assertPageSizeAndNumberEquals(int perPageLimit, int page, Pageable pageable) {
        assertThat(pageable.getPageSize()).isEqualTo(perPageLimit);
        assertThat(pageable.getPageNumber()).isEqualTo(page);
    }

}