package com.odde.bbuddy.common.page;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.view.Params;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

import static com.odde.bbuddy.common.page.PageView.PAGE_PARAM_NAME;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(NestedRunner.class)
public class PageViewTest {

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
            given_page_number_is(1);

            assertPreviousPageEquals(previousPageUrl(PAGE_PARAM_NAME, 0), pageViewModelMap("whatever message"));
        }

        @Test
        public void previous_page_when_on_first_page() {
            given_page_number_is(0);

            assertPreviousPageEquals(null, pageViewModelMap("whatever message"));
        }

    }

    private ModelMap pageViewModelMap(String currentPageMessage) {
        return new PageView(currentPageMessage, mockCurrentPage).getModelMap();
    }

    private void assertPreviousPageEquals(String previousPageUrl, ModelMap modelMap) {
        assertThat(modelMap.get("previousPageUrl")).isEqualTo(previousPageUrl);
    }

    private String previousPageUrl(String paramName, int paramValue) {
        Params previousPageUrl = new Params();
        previousPageUrl.add(paramName, valueOf(paramValue));
        return previousPageUrl.getQuery();
    }

    private void given_page_number_is(int page) {
        when(mockCurrentPage.number()).thenReturn(page);
    }
}
