package com.odde.bbuddy.common.controller;

import org.springframework.ui.Model;

public class ControllerHelper {
    public static Runnable setMessage(Model model, String message) {
        return () -> model.addAttribute("message", message);
    }
}
