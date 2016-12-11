package com.odde.bbuddy.game.domain;

public class Hangman {
    int tries = 12;

    public int getTries() {
        return tries;
    }

    public void input(String a) {
        tries--;
    }
}
