package com.odde.bbuddy.home.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.HOME;

@Component
@RequestScope
public class HomeView extends ModelAndView {

    public HomeView() {
        setViewName(HOME);
    }
}
