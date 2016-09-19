package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableTransaction;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_LIST;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class TransactionControllerListTest {

    private static final Date DATE = parseDay("2016-08-15");
    private static final int AMOUNT = 100;
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Model mockModel = mock(Model.class);

    @Test
    public void go_to_transaction_list_page() {
        assertThat(showAllTransactions()).isEqualTo("transactions/index");
    }

    @Test
    public void show_all_transactions() {
        given_exists_transactions(transaction(Income, "Description", DATE, AMOUNT));

        showAllTransactions();

        PresentableTransactions pts = verifyAddPresentableTransactions();
        assertPresentableTransactionEquals(
                pts,
                expected(Income, "Description", DATE, AMOUNT));
    }

    @Test
    public void show_no_transaction() {
        controller.noTransactionMessage = "no transaction message";

        showAllTransactions();

        PresentableTransactions pts = verifyAddPresentableTransactions();
        assertThat(pts.message()).isEqualTo("no transaction message");
        assertThat(pts.display()).isEqualTo("hidden");
    }

    private PresentableTransaction expected(Type type, String description, Date date, int amount) {
        PresentableTransaction expected = new PresentableTransaction();
        expected.setType(type);
        expected.setDescription(description);
        expected.setDate(date);
        expected.setAmount(amount);
        return expected;
    }

    private Transaction transaction(Type type, String description, Date date, int amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setAmount(amount);
        return transaction;
    }

    private void given_exists_transactions(Transaction transaction) {
        doAnswer(invocation -> {
            Consumer<Transaction> consumer = (Consumer<Transaction>) invocation.getArguments()[0];
            consumer.accept(transaction);
            return null;
        }).when(mockTransactions).processAll(any(Consumer.class));
    }

    private String showAllTransactions() {
        return controller.index(mockModel);
    }

    private PresentableTransactions verifyAddPresentableTransactions() {
        ArgumentCaptor<PresentableTransactions> captor = ArgumentCaptor.forClass(PresentableTransactions.class);
        verify(mockModel).addAttribute(eq("transactions"), captor.capture());
        return captor.getValue();
    }

    private void assertPresentableTransactionEquals(List<PresentableTransaction> presentableTransactions, PresentableTransaction expected) {
        presentableTransactions.forEach(
                actual -> assertThat(actual).isEqualToComparingFieldByField(expected));
    }

}
