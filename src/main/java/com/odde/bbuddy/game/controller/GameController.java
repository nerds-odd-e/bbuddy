package com.odde.bbuddy.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    @GetMapping("/yyy")
    public String startGame(){
        return "yyy";
    }
}
