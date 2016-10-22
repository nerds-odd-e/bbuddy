package com.odde.bbuddy.common.builder;

import com.odde.bbuddy.common.page.CurrentPage;
import com.odde.bbuddy.common.page.PageView;

import static com.odde.bbuddy.common.page.CurrentPage.FIRST_PAGE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageViewBuilder {

    private int pageNumber = FIRST_PAGE;
    private String currentPageMessage = "whatever message";

    public static PageViewBuilder builder() {
        return new PageViewBuilder();
    }

    public PageView build() {
        return new PageView(currentPageMessage, stubCurrentPage());
    }

    private CurrentPage stubCurrentPage() {
        CurrentPage stubCurrentPage = mock(CurrentPage.class);
        when(stubCurrentPage.number()).thenReturn(pageNumber);
        return stubCurrentPage;
    }

    public PageViewBuilder currentPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageViewBuilder currentPageMessage(String currentPageMessage) {
        this.currentPageMessage = currentPageMessage;
        return this;
    }
}
