package com.odde.bbuddy.common.builder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableBuilder {

    public static PageableBuilder defaultPageable() {
        return new PageableBuilder();
    }

    public Pageable build() {
        return new PageRequest(1, 10);
    }
}
