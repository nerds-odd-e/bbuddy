package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.View;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;

public class ControllerTestHelper {
    public static void spyOnDisplayOf(View view) {
        doNothing().when(view).display(Mockito.anyObject());
    }
}