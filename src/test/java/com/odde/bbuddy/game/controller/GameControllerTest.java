package com.odde.bbuddy.game.controller;

import com.odde.bbuddy.game.domain.Hangman;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GameControllerTest {
    @Test
    public void input() {
        Hangman mockHangman = mock(Hangman.class);
        when(mockHangman.getTries()).thenReturn(11);
        GameController gameController = new GameController(mockHangman);
        Model mockModel = mock(Model.class);
        gameController.getInput("a",mockModel);
        verify(mockModel).addAttribute("tries",11);
        verify(mockHangman).input("a");

    }
}