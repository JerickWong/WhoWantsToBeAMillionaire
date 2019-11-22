package com.example.whowantstobeamillionaire;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    ArrayList<Question> questions;
    static int gameState;
    Random random;
    int playerScore;

    public int absoluteValue(int n){
        if (n < 0)
            return -n;
        return n;
    }

    public GameEngine(){
        questions = new ArrayList<>();
        /* game states:
        * 0 - game not started
        * 1 - waiting for answer; timer running
        * 2 - correct answer; proceed to next question; timer stops
        * 3 - game over; timer stops; game stops */
        gameState = 0;
        random = new Random();
        playerScore = 0;
    }
}
