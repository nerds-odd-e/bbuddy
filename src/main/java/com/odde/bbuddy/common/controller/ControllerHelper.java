package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.Message;
import org.springframework.ui.Model;

public class ControllerHelper {
    public static Runnable thenSetMessage(Model model, String message) {
        return () -> new Message(model, message);
    }

}
