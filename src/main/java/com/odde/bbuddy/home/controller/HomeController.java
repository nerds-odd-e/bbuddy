package com.odde.bbuddy.home.controller;

import com.odde.bbuddy.common.Outbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {
    @Autowired
    private Outbox outbox;

    @RequestMapping(value = {"/"}, method = GET)
    public String index(Model model){
        outbox.send(null);
        return "home";
    }
}
