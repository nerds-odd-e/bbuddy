package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction.Type;
import lombok.Setter;

@Setter
public class PresentableTransaction {

    private Type type;
    private String description;
    private String date;
    private int amount;

}
