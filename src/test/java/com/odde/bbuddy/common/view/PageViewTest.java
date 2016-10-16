package com.odde.bbuddy.common.view;

import com.nitorcreations.junit.runners.NestedRunner;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

import static com.odde.bbuddy.common.view.PageView.PAGE_PARAM_NAME;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(NestedRunner.class)
public class PageViewTest {

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);

    public class PageNumber {

        @Test
        public void param_page_exists() {
            given_page_is(5);

            assertThat(pageViewModelMap("Current page is %s").get("currentPage")).isEqualTo("Current page is 6");
        }
        
        @Test
        public void no_param_page() {
            assertThat(pageViewModelMap("Current page is %s").get("currentPage")).isEqualTo("Current page is 1");
        }

    }

    public class PreviousPage {

        @Test
        public void previous_page_when_not_on_first_page() {
            given_page_is(1);

            assertPreviousPageEquals(previousPageUrl(PAGE_PARAM_NAME, 0), pageViewModelMap("whatever message"));
        }

        @Test
        public void previous_page_when_on_first_page() {
            given_page_is(0);

            assertPreviousPageEquals(null, pageViewModelMap("whatever message"));
        }

        @Test
        public void no_param_page() {
            assertPreviousPageEquals(null, pageViewModelMap("whatever message"));
        }

    }

    private ModelMap pageViewModelMap(String currentPageMessage) {
        return new PageView(mockRequest, currentPageMessage).getModelMap();
    }

    private void assertPreviousPageEquals(String previousPageUrl, ModelMap modelMap) {
        assertThat(modelMap.get("previousPageUrl")).isEqualTo(previousPageUrl);
    }

    private String previousPageUrl(String paramName, int paramValue) {
        Params previousPageUrl = new Params();
        previousPageUrl.add(paramName, valueOf(paramValue));
        return previousPageUrl.getQuery();
    }

    private void given_page_is(int page) {
        when(mockRequest.getParameter(PAGE_PARAM_NAME)).thenReturn(valueOf(page));
    }
}
