package com.odde.bbuddy.session.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class AuthenticationFailedTest {

    SessionController controller = new SessionController();

    @Test
    public void back_to_sign_in() {
        ModelAndView actual = controller.signIn(null, null);

        assertEquals("signin", actual.getViewName());
    }

    @Test
    public void message_when_sign_in_error() {
        ModelAndView actual = controller.signIn("any error", null);

        assertEquals("Invalid username and password!", actual.getModel().get("message"));
        assertEquals("danger", actual.getModel().get("type"));
        assertEquals("signin", actual.getViewName());
    }

    @Test
    public void message_when_logout() {
        ModelAndView actual = controller.signIn(null, "something logout");

        assertEquals("You've been logged out successfully.", actual.getModel().get("message"));
        assertEquals("info", actual.getModel().get("type"));
        assertEquals("signin", actual.getViewName());
    }
}
