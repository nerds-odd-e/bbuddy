package com.odde.bbuddy.acceptancetest.steps;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {

    private String type;
    private String description;
    private String date;
    private Integer amount;

}
