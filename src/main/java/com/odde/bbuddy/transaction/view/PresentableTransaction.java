package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction.Type;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;

@Setter
@Getter
public class PresentableTransaction {

    private Type type;
    private String description;
    private Date date;
    private int amount;

    public String dateForView() {
        return new SimpleDateFormat(DAY).format(date);
    }

    public String[] allViewText() {
        return new String[]{type.name(), description, dateForView(), String.valueOf(amount)};
    }
}
