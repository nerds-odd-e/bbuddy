package com.odde.bbuddy.common.page;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.view.Params;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(NestedRunner.class)
public class PageViewTest {

    private static final String PAGE_PARAM_NAME = "page";
    private static final int FIRST_PAGE = 0;
    CurrentPage mockCurrentPage = mock(CurrentPage.class);

    public class PageNumber {

        @Test
        public void param_page_exists() {
            given_page_number_is(5);

            assertThat(pageViewModelMap("Current page is %s").get("currentPage")).isEqualTo("Current page is 6");
        }
        
    }

    public class PreviousPage {

        @Test
        public void previous_page_when_not_on_first_page() {
            given_page_number_is(2);

            assertPreviousPageEquals(pageUrl(PAGE_PARAM_NAME, 1), pageViewModelMap("whatever message"));
        }

        @Test
        public void previous_page_when_on_first_page() {
            given_page_number_is(FIRST_PAGE);

            assertPreviousPageEquals(null, pageViewModelMap("whatever message"));
        }

    }

    public class NextPage {

        PageView view = new PageView("whatever message", mockCurrentPage);

        @Test
        public void next_page_when_not_on_last_page() {
            given_page_number_is(4);

            view.display(6);

            assertThat(view.getModelMap().get("nextPageUrl")).isEqualTo(pageUrl(PAGE_PARAM_NAME, 5));
        }
        
        @Test
        public void next_page_when_on_last_page() {
            given_page_number_is(4);

            view.display(5);

            assertThat(view.getModelMap().get("nextPageUrl")).isEqualTo(null);
        }
    }

    private ModelMap pageViewModelMap(String currentPageMessage) {
        return new PageView(currentPageMessage, mockCurrentPage).getModelMap();
    }

    private void assertPreviousPageEquals(String previousPageUrl, ModelMap modelMap) {
        assertThat(modelMap.get("previousPageUrl")).isEqualTo(previousPageUrl);
    }

    private String pageUrl(String paramName, int paramValue) {
        Params pageUrl = new Params();
        pageUrl.add(paramName, valueOf(paramValue));
        return pageUrl.getQuery();
    }

    private void given_page_number_is(int page) {
        when(mockCurrentPage.number()).thenReturn(page);
    }
}
