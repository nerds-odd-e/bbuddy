package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.common.view.CombinableModelAndView;
import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.transaction.repo.Transaction;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS_INDEX;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Component
@RequestScope
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ModelAndView implements View<Transaction>, CombinableModelAndView {

    private final List<PresentableTransaction> allPresentableTransactions = new ArrayList<>();

    @Builder
    public PresentableTransactions(
            @Value("${transactions.list.empty}") String noTransactionMessage) {
        addObject("transactions", allPresentableTransactions);
        showMessage(noTransactionMessage);
        setViewName(TRANSACTIONS_INDEX);
    }

    private void showMessage(String noTransactionMessage) {
        addObject("message", noTransactionMessage);
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        return pt;
    }

    @Override
    public void display(Transaction transaction) {
        allPresentableTransactions.add(presentableTransactionFrom(transaction));
        hideMessage();
    }

    private void hideMessage() {
        getModelMap().remove("message");
    }

    @Override
    public ModelAndView original() {
        return this;
    }
}
