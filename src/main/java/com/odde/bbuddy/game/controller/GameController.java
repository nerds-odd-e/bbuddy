package com.odde.bbuddy.game.controller;

import com.odde.bbuddy.game.domain.Hangman;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
    private final Hangman mockHangman;

    public GameController(Hangman mockHangman) {
        this.mockHangman = mockHangman;
    }

    @GetMapping("/yyy")
    public String startGame(){
        return "yyy";
    }

    @PostMapping("/yyy")
    public String getInput(String a, Model mockModel){

        mockModel.addAttribute("tries",mockHangman.getTries());
        return "yyy";
    }
}
