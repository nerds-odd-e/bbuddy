package com.odde.bbuddy.common.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableBuilder {

    private int page = 1;

    public static PageableBuilder builder() {
        return new PageableBuilder();
    }

    public Pageable build() {
        return new PageRequest(page, 10);
    }

}
