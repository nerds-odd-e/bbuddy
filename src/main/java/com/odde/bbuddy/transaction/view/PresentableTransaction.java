package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.repo.Transaction.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.format.DateTimeFormatter.ofPattern;

@Setter
@Getter
public class PresentableTransaction {

    private Type type;
    private String description;
    private LocalDate date;
    private int amount;

    public String dateForView() {
        return date.format(ofPattern(DAY));
    }

}
