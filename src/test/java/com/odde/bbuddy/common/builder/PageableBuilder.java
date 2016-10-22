package com.odde.bbuddy.common.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.odde.bbuddy.common.page.CurrentPage.FIRST_PAGE;

public class PageableBuilder {

    private int page = FIRST_PAGE;

    public static PageableBuilder builder() {
        return new PageableBuilder();
    }

    public Pageable build() {
        return new PageRequest(page, 10);
    }

}
