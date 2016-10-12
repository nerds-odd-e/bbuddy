package com.odde.bbuddy.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableFactory {
    private final int perPageLimit;

    public PageableFactory(
            @Value("${application.perPageLimit}") int perPageLimit) {
        this.perPageLimit = perPageLimit;
    }

    public Pageable create(int page) {
        return new PageRequest(page, perPageLimit);
    }
}
