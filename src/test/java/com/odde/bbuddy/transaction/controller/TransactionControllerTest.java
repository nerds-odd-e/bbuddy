package com.odde.bbuddy.transaction.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableTransaction;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_ADD;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {

        @Test
        public void should_go_to_transaction_add_page() {
            assertThat(addTransaction()).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_show_all_transaction_types() {
            addTransaction();

            verify(mockModel).addAttribute("types", Type.values());
        }

        private String addTransaction() {
            return controller.addTransaction(mockModel);
        }
    }

    public class AddSubmitSuccess {

        @Before
        public void given_add_transaction_will_success() {
            given_add_transaction_will(success());
        }

        @Test
        public void should_go_to_transaction_add_page() {
            assertThat(submitTransactionAdd(TransactionControllerTest.this.transaction)).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_show_all_transaction_types_after_submit() {
            submitTransactionAdd(TransactionControllerTest.this.transaction);

            verify(mockModel).addAttribute("types", Type.values());
        }

        @Test
        public void should_add_transaction() {
            submitTransactionAdd(TransactionControllerTest.this.transaction);

            verify(mockTransactions).add(transaction);
        }

        @Test
        public void should_return_add_success_message_to_page() {
            controller.successMessage = "a success message";

            submitTransactionAdd(TransactionControllerTest.this.transaction);

            verify(mockModel).addAttribute("message", "a success message");
        }
    }

    public class AddSubmitFailed {

        @Test
        public void should_return_add_failed_message_to_page() {
            given_add_transaction_will(failed());
            controller.failedMessage = "a failed message";

            submitTransactionAdd(TransactionControllerTest.this.transaction);

            verify(mockModel).addAttribute("message", "a failed message");
        }

    }

    public class Valid {

        Transaction invalidTransaction = new Transaction();

        @Before
        public void given_has_some_field_error() {
            given_has_field_error(true);
        }

        @Test
        public void should_not_add_transaction() {
            submitTransactionAdd(invalidTransaction);

            verify(mockTransactions, never()).add(invalidTransaction);
        }

        @Test
        public void should_go_to_add_transaction_page() {
            assertThat(submitTransactionAdd(invalidTransaction)).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_show_all_transaction_types() {
            submitTransactionAdd(invalidTransaction);

            verify(mockModel).addAttribute("types", Type.values());
        }

    }

    public class List {

        Date date = parseDay("2016-08-15");
        int amount = 100;

        @Test
        public void should_go_to_transaction_list_page() {
            assertThat(showAllTransactions()).isEqualTo("transactions/index");
        }

        @Test
        public void should_show_all_transactions() {
            given_exists_transactions(transaction(Income, "Description", date, amount));

            showAllTransactions();

            PresentableTransactions pts = verifyAddPresentableTransactions();
            assertPresentableTransactionEquals(pts, expected(Income, "Description", date, amount));
            assertThat(pts.message()).isEqualTo("");
            assertThat(pts.display()).isEqualTo("");
        }

        @Test
        public void should_show_no_transaction() {
            controller.noTransactionMessage = "no transaction message";

            showAllTransactions();

            PresentableTransactions pts = verifyAddPresentableTransactions();
            assertThat(pts).isEmpty();
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

        private void assertPresentableTransactionEquals(PresentableTransactions presentableTransactions, PresentableTransaction expected) {
            presentableTransactions.forEach(
                    actual -> assertThat(actual).isEqualToComparingFieldByField(expected));
        }
    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private String submitTransactionAdd(Transaction transaction) {
        return controller.submitAddTransaction(transaction, stubBindingResult, mockModel);
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }
}
