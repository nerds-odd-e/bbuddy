package com.odde.bbuddy.common.page;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.view.Params;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.odde.bbuddy.common.builder.PageViewBuilder.builder;
import static com.odde.bbuddy.common.page.CurrentPage.FIRST_PAGE;
import static com.odde.bbuddy.common.page.PageView.PAGE_PARAM_NAME;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NestedRunner.class)
public class PageViewTest {

    PageView view;

    public class PageNumber {

        @Test
        public void param_page_exists() {
            view = builder()
                    .currentPage(5)
                    .currentPageMessage("Current page is %s")
                    .build();

            assertThat(view.getModelMap().get("currentPage")).isEqualTo("Current page is 5");
        }
        
    }

    public class PreviousPage {

        @Test
        public void previous_page_when_not_on_first_page() {
            view = builder().currentPage(2).build();

            assertPreviousPageEquals(pageUrl(PAGE_PARAM_NAME, 1));
        }

        @Test
        public void previous_page_when_on_first_page() {
            view = builder().currentPage(FIRST_PAGE).build();

            assertPreviousPageEquals(null);
        }

        private void assertPreviousPageEquals(String expected) {
            assertThat(view.getModelMap().get("previousPageUrl")).isEqualTo(expected);
        }

    }

    public class NextPage {

        @Test
        public void next_page_when_not_on_last_page() {
            view = builder().currentPage(4).build();

            totalPageCountIs(5);

            assertNextPageUrlEquals(pageUrl(PAGE_PARAM_NAME, 5));
        }

        @Test
        public void next_page_when_on_last_page() {
            view = builder().currentPage(4).build();

            totalPageCountIs(4);

            assertNextPageUrlEquals(null);
        }

        private void assertNextPageUrlEquals(String expected) {
            assertThat(view.getModelMap().get("nextPageUrl")).isEqualTo(expected);
        }

        private void totalPageCountIs(int totalPageCount) {
            view.display(totalPageCount);
        }

    }

    private String pageUrl(String paramName, int paramValue) {
        Params pageUrl = new Params();
        pageUrl.add(paramName, valueOf(paramValue));
        return pageUrl.getQuery();
    }

}
