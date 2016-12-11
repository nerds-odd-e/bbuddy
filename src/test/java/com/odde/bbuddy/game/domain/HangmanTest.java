package com.odde.bbuddy.game.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HangmanTest {
    @Test
    public void gameinit() {
        Hangman hangman = new Hangman();
        assertEquals(12, hangman.getTries());
    }

    @Test
    public void inputVowel() {
        Hangman hangman = new Hangman();
        hangman.input("a");
        assertEquals(11, hangman.getTries());
    }

}