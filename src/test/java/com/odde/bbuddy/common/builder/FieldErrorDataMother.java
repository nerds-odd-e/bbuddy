package com.odde.bbuddy.common.builder;

import org.springframework.validation.FieldError;

public class FieldErrorDataMother {

    public static FieldError fieldError(String field) {
        return new FieldError("notUsedObjectName", field, "not used default error message");
    }
}
