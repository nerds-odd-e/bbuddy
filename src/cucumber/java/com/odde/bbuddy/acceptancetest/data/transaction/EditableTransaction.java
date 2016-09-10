package com.odde.bbuddy.acceptancetest.data.transaction;

import lombok.Getter;

@Getter
public class EditableTransaction {

    private String type;
    private String description;
    private String date;
    private String amount;

    public EditableTransaction populateAllEmptyAndDefaultData() {
        type = "Income";
        description = "";
        date = "";
        amount = "";
        return this;
    }
}
