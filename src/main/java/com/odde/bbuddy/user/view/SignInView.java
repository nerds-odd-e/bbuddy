package com.odde.bbuddy.user.view;

import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.user.domain.AuthenticationResult;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;
import static com.odde.bbuddy.common.view.MessagePropertyNamesWithSyntax.AUTHENTICATION_FAILED;
import static com.odde.bbuddy.common.view.MessagePropertyNamesWithSyntax.AUTHENTICATION_LOGOUT;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Component
@RequestScope
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@Builder
public class SignInView extends ModelAndView implements View<AuthenticationResult> {

    private final String failedMessage;
    private final String logoutMessage;

    public SignInView(
            @Value(AUTHENTICATION_FAILED) String failedMessage,
            @Value(AUTHENTICATION_LOGOUT) String logoutMessage) {
        this.failedMessage = failedMessage;
        this.logoutMessage = logoutMessage;
        setViewName(SIGNIN);
    }

    private void setMessageAndType(String message, String type) {
        addObject("message", message);
        addObject("type", type);
    }

    @Override
    public void display(AuthenticationResult authenticationResult) {
        authenticationResult
                .error(() -> setMessageAndType(failedMessage, "danger"))
                .logout(() -> setMessageAndType(logoutMessage, "info"));
    }

}
