package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction;
import lombok.Setter;

@Setter
public class PresentableTransaction {

    private Transaction.Type type;
    private String description;
    private String date;
    private int amount;

}
