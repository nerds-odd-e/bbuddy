package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Message {

    private final Model model;

    @Autowired
    public Message(Model model) {
        this.model = model;
    }

    public void display(String message) {
        model.addAttribute("message", message);
    }
}
