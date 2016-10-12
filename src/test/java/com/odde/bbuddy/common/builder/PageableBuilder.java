package com.odde.bbuddy.common.builder;

import com.odde.bbuddy.common.controller.PageableFactory;
import org.springframework.data.domain.Pageable;

public class PageableBuilder {

    public static PageableBuilder defaultPageable() {
        return new PageableBuilder();
    }

    public Pageable build() {
        return new PageableFactory(10).create(1);
    }
}
