package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.transaction.domain.Transaction;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_INDEX;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ModelAndView implements View<Transaction> {

    private final List<PresentableTransaction> presentableTransactions = new ArrayList<>();

    @Builder
    public PresentableTransactions(
            @Value("${transaction.list.empty}") String noTransactionMessage) {
        addObject("transactions", presentableTransactions);
        hiddenViewAndShowMessage(noTransactionMessage);
        setViewName(TRANSACTION_INDEX);
    }

    private void hiddenViewAndShowMessage(String noTransactionMessage) {
        addObject("hidden", "hidden");
        addObject("message", noTransactionMessage);
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        return pt;
    }

    @Override
    public void display(Transaction transaction) {
        presentableTransactions.add(presentableTransactionFrom(transaction));
        showViewAndHiddenMessage();
    }

    private void showViewAndHiddenMessage() {
        getModelMap().remove("hidden");
        getModelMap().remove("message");
    }

}
