package com.odde.bbuddy.home.controller;

import com.odde.bbuddy.home.view.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.ROOT;

@Controller
@RequestMapping(ROOT)
public class HomeController {

    @Autowired
    HomeView homeView;

    @GetMapping
    public ModelAndView index(){
        return homeView;
    }
}
