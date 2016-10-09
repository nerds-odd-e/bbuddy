package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Message implements View<String> {

    private final Model model;

    @Autowired
    public Message(Model model) {
        this.model = model;
    }

    @Override
    public void display(String message) {
        model.addAttribute("message", message);
    }
}
