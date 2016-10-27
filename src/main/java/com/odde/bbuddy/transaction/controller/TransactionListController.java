package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.common.page.PageView;
import com.odde.bbuddy.common.page.PageableFactory;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableSummaryOfTransactions;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS;

@Controller
@RequestMapping(TRANSACTIONS)
public class TransactionListController {
    private final Transactions transactions;
    private final PresentableTransactions presentableTransactions;
    private final PresentableSummaryOfTransactions presentableSummaryOfTransactions;
    private final PageableFactory pageableFactory;
    private final PageView pageView;

    @Autowired
    public TransactionListController(Transactions transactions, PresentableTransactions presentableTransactions, PresentableSummaryOfTransactions presentableSummaryOfTransactions, PageableFactory pageableFactory, PageView pageView) {
        this.transactions = transactions;
        this.presentableTransactions = presentableTransactions;
        this.presentableSummaryOfTransactions = presentableSummaryOfTransactions;
        this.pageableFactory = pageableFactory;
        this.pageView = pageView;
    }

    @GetMapping
    public ModelAndView index() {
        transactions.processAll(presentableTransactions::display, pageableFactory.create())
                .withSummary(presentableSummaryOfTransactions::display)
                .withTotalPageCount(pageView::display);
        return presentableTransactions.combineWith(pageView, presentableSummaryOfTransactions);
    }

}
