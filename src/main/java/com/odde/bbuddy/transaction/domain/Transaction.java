package com.odde.bbuddy.transaction.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Transaction {

    private Type type;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer amount;

    public enum Type {
        Income
    }

}
