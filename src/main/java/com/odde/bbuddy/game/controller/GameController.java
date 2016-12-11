package com.odde.bbuddy.game.controller;

import com.odde.bbuddy.game.domain.Hangman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
    private final Hangman mockHangman;
    @Autowired
    public GameController(Hangman mockHangman) {
        this.mockHangman = mockHangman;
    }

    @GetMapping("/yyy")
    public String startGame(Model model){
        model.addAttribute("tries",mockHangman.getTries());
        return "yyy";
    }

    @PostMapping("/yyy")
    public String getInput(String a, Model mockModel){
        mockHangman.input(a);
        mockModel.addAttribute("tries",mockHangman.getTries());
        return "yyy";
    }

}
