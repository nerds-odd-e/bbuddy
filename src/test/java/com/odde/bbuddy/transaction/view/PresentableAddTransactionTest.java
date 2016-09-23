package com.odde.bbuddy.transaction.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;

import static com.odde.bbuddy.transaction.domain.Transaction.Type.values;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentableAddTransactionTest {

    @Test
    public void should_pass_type_values_to_page() {
        HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
        PresentableAddTransaction presentableAddTransaction = new PresentableAddTransaction(mockHttpServletRequest);

        presentableAddTransaction.display();

        verify(mockHttpServletRequest).setAttribute("types", values());
    }
}
