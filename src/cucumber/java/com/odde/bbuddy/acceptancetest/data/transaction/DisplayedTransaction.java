package com.odde.bbuddy.acceptancetest.data.transaction;

import com.odde.bbuddy.acceptancetest.data.transformer.DayToLocalDateTransformer;
import com.odde.bbuddy.common.BeanUtils;
import com.odde.bbuddy.transaction.repo.Transaction;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.odde.bbuddy.common.Formats.DAY;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class DisplayedTransaction {
    private Transaction.Type type;
    private String description;
    @XStreamConverter(DayToLocalDateTransformer.class)
    private LocalDate date;
    private Integer amount;

    public static List<Transaction> expectedTransactions(List<DisplayedTransaction> expected) {
        return expected.stream().map(DisplayedTransaction::copy).collect(toList());
    }

    private static Transaction copy(DisplayedTransaction each) {
        Transaction target = new Transaction();
        BeanUtils.copyProperties(target, each);
        return target;
    }

    public void assertAllFieldsDisplayedIn(String text) {
        assertThat(text).contains(type.name(), description, dateForView(), String.valueOf(amount));
    }

    private String dateForView() {
        return date.format(ofPattern(DAY));
    }

}
