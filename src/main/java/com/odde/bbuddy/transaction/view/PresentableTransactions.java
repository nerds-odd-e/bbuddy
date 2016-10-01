package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PresentableTransactions extends ModelAndView {

    public PresentableTransactions(
            @Autowired Transactions transactions,
            @Value("${transaction.list.empty}") String noTransactionMessage) {
        List<PresentableTransaction> pts = presentableTransactionsFrom(transactions);
        addObject("transactions", pts);
        if (pts.isEmpty())
            hiddenViewAndShowMessage(noTransactionMessage);
        showTotals();
        setViewName(TRANSACTION_INDEX);
    }

    private void showTotals() {
        addObject("totalIncome", 14000);
        addObject("totalOutcome", 30000);
        addObject("balance", -16000);
    }

    private void hiddenViewAndShowMessage(String noTransactionMessage) {
        addObject("hidden", "hidden");
        addObject("message", noTransactionMessage);
    }

    private List<PresentableTransaction> presentableTransactionsFrom(Transactions transactions) {
        List<PresentableTransaction> pts = new ArrayList<>();
        transactions.processAll(transaction -> pts.add(presentableTransactionFrom(transaction)));
        return pts;
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        return pt;
    }

}
