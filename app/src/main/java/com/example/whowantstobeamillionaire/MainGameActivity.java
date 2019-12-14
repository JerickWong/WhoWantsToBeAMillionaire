package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainGameActivity extends AppCompatActivity {

    TextView questionTextView, timerTextView;
    Button aButton, bButton, cButton, dButton;
    Button score500, score1000, score2000, score3000, score5000, score7500,
            score15k, score30k, score60k, score125k, score250k, score1M;
    ImageButton lifeline5050, lifelineDoubleDip, lifelineNextQuestion;
    SoundBank soundBank;
    String score, currentQuestionScore;
    boolean used5050, usedNextQuestion, usedDoubleDip, endGame, doubleDipActivated;
    ArrayList<Button> questions;
    String category, answer, secondAnswer;
    private long startTime, loopTime; // Loop start time and loop duration
    private long DELAY = 33; // Delay in milliseconds between screen refreshes
    Question currentQuestion;
    int questionWorth, currentScore = 0, round = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

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
        doubleDipActivated = false;

        score = "0";
        currentQuestionScore = "0";
        secondAnswer = "";

        initializeQuestions();
        GameData.initializeDBQuestions();
        playStartMusic();
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
        while(!endGame) {
            startTime = SystemClock.uptimeMillis();
            answer = "";

            String worth = questions.get(round-1).getText().toString();
            questionWorth = Integer.parseInt(worth.replaceAll(",",""));

            if (questionWorth < 1000) {
                category = "0";
                currentQuestion= GameData.question0.get(0);
                GameData.question0.remove(0);
                setQuestion(currentQuestion);
            }
            else if (questionWorth < 15000)
                category = "1000";
            else if (questionWorth == 1000000)
                category = "1000000";

            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
            if (loopTime<DELAY)
                delayTime();

            runTimer();
            checkAnswer();

            round++;
        }
    }

    public void delayTime() {
        Thread thread = new Thread ();
        // wait for delay
        try {
            thread.sleep(DELAY - loopTime);
        } catch (InterruptedException e) {
            Log.e("Interrupted", "Interrupted wile sleeping");
        }
    }

    public void runTimer() {
        int timer = 60;
        Thread thread = new Thread ();

        // 60 seconds timer
        while(timer > 0) {
            timerTextView.setText(timer);
            try{
                timer--;
                thread.sleep(60000);   // 60 second timer
                if (lifelineNextQuestion.isPressed() && !usedNextQuestion)
                    thread.interrupt();

                if (aButton.isPressed() || bButton.isPressed() || cButton.isPressed() || dButton.isPressed()) {

                    // wait half a second for second answer to be set (for double dip)
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // normal answer
                    if (!doubleDipActivated)
                        thread.interrupt();

                    // used double dip
                    else if (!secondAnswer.matches(""))
                        thread.interrupt();
                }

            } catch (InterruptedException e) {
                Log.e("Interrupted the timer", "Sleep interrupted");
                break; // questionable, will need to test
            }
        }
    }

    public void checkAnswer() {
        if (currentQuestion.getAnswer().equals(answer) || currentQuestion.getAnswer().equals(secondAnswer)) {
            currentScore += questionWorth;
            doubleDipActivated = false;

            if (category.equals("1,000,000"))
                endGame = true;
        } else {
            currentScore = Integer.parseInt(category);
            endGame = true;
        }
    }

    public void setQuestion(Question question) {
        questionTextView.setText(question.getQuestion());
        aButton.setText(question.getOptionA());
        bButton.setText(question.getOptionB());
        cButton.setText(question.getOptionC());
        dButton.setText(question.getOptionD());
    }

    public void chooseA(View view) {
        if (answer.matches(""))
            answer = aButton.getText().toString();
        else if (doubleDipActivated)
            secondAnswer = aButton.getText().toString();

    }

    public void chooseB (View view) {
        if (answer.matches(""))
            answer = bButton.getText().toString();
        else if (doubleDipActivated)
            secondAnswer = bButton.getText().toString();
    }

    public void chooseC (View view) {
        if (answer.matches(""))
            answer = cButton.getText().toString();
        else if (doubleDipActivated)
            secondAnswer = cButton.getText().toString();
    }

    public void chooseD (View view) {
        if (answer.matches(""))
            answer = dButton.getText().toString();
        else if (doubleDipActivated)
            secondAnswer = dButton.getText().toString();
    }

    // LIFELINES
    public void use5050(View view) {
        if (!used5050) {
            bButton.setText("");
            cButton.setText("");
            used5050 = true;
        }
    }

    public void useDoubleDip(View view) {
        if (!usedDoubleDip) {
            doubleDipActivated = true;
            usedDoubleDip = true;
        }
    }

    public void useNextQuestion(View view) {
        if (!usedNextQuestion) {

            if (currentQuestion.getCategory().equals("0")) {
                currentQuestion = GameData.question0.get(0);
                GameData.question0.remove(0);
            } else if (currentQuestion.getCategory().equals("1,000")) {
                currentQuestion = GameData.question1000.get(0);
                GameData.question1000.remove(0);
            } else if (currentQuestion.getCategory().equals("15,000")) {
                currentQuestion = GameData.question15k.get(0);
                GameData.question15k.remove(0);
            } else if (currentQuestion.getCategory().equals("1,000,000")) {
                currentQuestion = GameData.question1M.get(0);
                GameData.question1M.remove(0);
            }


            setQuestion(currentQuestion);
            runTimer();
            startGame();
            usedNextQuestion = true;
        }
    }
}

