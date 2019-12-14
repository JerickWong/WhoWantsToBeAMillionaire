package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainGameActivity extends AppCompatActivity {

    TextView questionTextView;
    Button aButton, bButton, cButton, dButton;
    Button score500, score1000, score2000, score3000, score5000, score7500,
            score15k, score30k, score60k, score125k, score250k, score1M;
    ImageButton lifeline5050, lifelineDoubleDip, lifelineNextQuestion;
    SoundBank soundBank;
    String score, currentQuestionScore;
    boolean used5050, usedNextQuestion, usedDoubleDip, endGame;
    ArrayList<Button> questions;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        aButton = (Button) findViewById(R.id.aButton);
        bButton = (Button) findViewById(R.id.bButton);
        cButton = (Button) findViewById(R.id.cButton);
        dButton = (Button) findViewById(R.id.dButton);

        score500 = (Button) findViewById(R.id.score500);
        score1000 = (Button) findViewById(R.id.score1000);
        score2000 = (Button) findViewById(R.id.score2000);
        score3000 = (Button) findViewById(R.id.score3000);
        score5000 = (Button) findViewById(R.id.score5000);
        score7500 = (Button) findViewById(R.id.score7500);
        score15k = (Button) findViewById(R.id.score15k);
        score30k = (Button) findViewById(R.id.score30k);
        score60k = (Button) findViewById(R.id.score60k);
        score125k = (Button) findViewById(R.id.score125k);
        score250k = (Button) findViewById(R.id.score250k);
        score1M = (Button) findViewById(R.id.score1M);

        lifeline5050 = (ImageButton) findViewById(R.id.lifeline5050);
        lifelineDoubleDip = (ImageButton) findViewById(R.id.lifelineDoubleDip);
        lifelineNextQuestion = (ImageButton) findViewById(R.id.lifelineNextQuestion);

        used5050 = false;
        usedDoubleDip = false;
        usedNextQuestion = false;
        endGame = false;

        score = "0";
        currentQuestionScore = "0";

//        initializeQuestions();
//        playStartMusic();
    }

    public void initializeQuestions() {
        questions.add(score500);
        questions.add(score1000);
        questions.add(score2000);
        questions.add(score3000);
        questions.add(score5000);
        questions.add(score7500);
        questions.add(score15k);
        questions.add(score30k);
        questions.add(score60k);
        questions.add(score125k);
        questions.add(score250k);
        questions.add(score1M);
    }

    public void playStartMusic() {
        soundBank.playCommercialBreak();
        try {
            // 6 SECONDS
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startGame();
    }

    public void startGame() {
        int round = 1;
        while(!endGame) {
            int questionWorth;

            String worth = questions.get(round-1).getText().toString();
            questionWorth = Integer.parseInt(worth.replaceAll(",",""));

            if (questionWorth < 1000) {
                category = "0";
            }
            else if (questionWorth < 15000)
                category = "1000";
            else if (questionWorth == 1000000)
                category = "1000000";


            round++;
        }
    }

    public void use5050(View view) {

    }

    public void useDoubleDip(View view) {

    }

    public void useNextQuestion(View view) {

    }
}

