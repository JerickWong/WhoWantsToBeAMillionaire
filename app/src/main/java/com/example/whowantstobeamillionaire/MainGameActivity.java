package com.example.whowantstobeamillionaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainGameActivity extends AppCompatActivity {

    TextView questionTextView, timerTextView;
    Button aButton, bButton, cButton, dButton;
    Button score500, score1000, score2000, score3000, score5000, score7500,
            score15k, score30k, score60k, score125k, score250k, score1M;
    ImageButton lifeline5050, lifelineDoubleDip, lifelineNextQuestion;
    SoundBank soundBank;
    Question currentQuestion;
    int score, currentQuestionScore;
    String category, answer, secondAnswer;
    boolean used5050, usedNextQuestion, usedDoubleDip, endGame, doubleDipActivated;
    ArrayList<Button> questions;
    ArrayList<Question> question0, question1000, question15k, question1M;
    private long startTime, loopTime; // Loop start time and loop duration
    private long DELAY = 33; // Delay in milliseconds between screen refreshes
    int questionWorth, currentScore = 0, round = 1;
    DatabaseReference databaseQuestions, databasePlayers;
    Thread thread = new Thread();
//    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        DatabaseQuestions.initialize();

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

        score = 0;
        currentQuestionScore = 0;
        secondAnswer = "";
        questions = new ArrayList<>();
        question0 = DatabaseQuestions.questions0;
        question1000 = DatabaseQuestions.questions1k;
        question15k = DatabaseQuestions.questions15k;
        question1M = DatabaseQuestions.questions1M;
        soundBank = new SoundBank(this);

        initializeQuestions();
//        initializeDBQuestions();
        playStartMusic();

        questionTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                round++;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    public void initializeDBQuestions() {
//        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");
//
//        databaseQuestions.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                DataSnapshot categories = dataSnapshot.child("categories");
//
//                int currentScoreWorth = Integer.parseInt(questions.get(round-1).getText().toString().replaceAll(",", ""));
//                Log.d("SCORE WORTH", "" + currentScoreWorth);
//                if (currentScoreWorth < 1000) {
//                    category = "0";
//                }
//                else if (currentScoreWorth < 15000)
//                    category = "1,000";
//                else if (currentScoreWorth < 1000000)
//                    category = "15,000";
//                else if (currentScoreWorth == 1000000)
//                    category = "1,000,000";
//
//                Question question = categories.child(category).child("question"+round).getValue(Question.class);
//
//                if (!thread.isAlive()) {
//                    questionTextView.setText(question.getQuestion());
//                    aButton.setText(question.getOptionA());
//                    bButton.setText(question.getOptionB());
//                    cButton.setText(question.getOptionC());
//                    dButton.setText(question.getOptionD());
//                }
//
//                aButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        answer = aButton.getText().toString();
//                    }
//                });
//
//                bButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        answer = bButton.getText().toString();
//                    }
//                });
//
//                cButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        answer = cButton.getText().toString();
//                    }
//                });
//
//                dButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        answer = dButton.getText().toString();
//                    }
//                });
//
//                if (question.getAnswer().equals(answer)) {
//                    currentScoreWorth = Integer.parseInt(questions.get(round-1).getText().toString().replaceAll(",", ""));
//                    Log.d("SCORE WORTH", "" + currentScoreWorth);
//                    if (currentScoreWorth < 1000) {
//                        category = "0";
//                    }
//                    else if (currentScoreWorth < 15000)
//                        category = "1,000";
//                    else if (currentScoreWorth < 1000000)
//                        category = "15,000";
//                    else if (currentScoreWorth == 1000000)
//                        category = "1,000,000";
//
//                    question = categories.child(category).child("question"+round).getValue(Question.class);
//
//                    questionTextView.setText(question.getQuestion());
//                    aButton.setText(question.getOptionA());
//                    bButton.setText(question.getOptionB());
//                    cButton.setText(question.getOptionC());
//                    dButton.setText(question.getOptionD());
//                }
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//    }

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
            thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        startGame();
    }

    public void startGame() {
//        while(!endGame) {
//            startTime = SystemClock.uptimeMillis();
            answer = "";

            String worth = questions.get(round-1).getText().toString();
            questionWorth = Integer.parseInt(worth.replaceAll(",",""));

            if (questionWorth < 1000) {
                category = "0";
                Log.e("ARRAYLIST SIZE", "" + question0.size());
//                currentQuestion= question0.get(0);
//                question0.remove(0);
//                setQuestion(currentQuestion);
            }
            else if (questionWorth < 15000)
                category = "1000";
            else if (questionWorth == 1000000)
                category = "1000000";

            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
//            if (loopTime<DELAY)
//                delayTime();

//            runTimer();
//            checkAnswer();

            round++;
//        }
    }

    public void delayTime() {
        // wait for delay
        try {
            thread.sleep(DELAY - loopTime);
            Log.e("DELAYING TIME", "Inside Thread");
        } catch (InterruptedException e) {
            Log.e("Interrupted", "Interrupted wile sleeping");
        }
    }

    public void runTimer() {
        int timer = 60;

        // 60 seconds timer
        while(timer > 0) {
            timerTextView.setText(timer+"");
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
            answer = aButton.getText().toString();
         if (doubleDipActivated)
            secondAnswer = aButton.getText().toString();

        databaseQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                answer = aButton.getText().toString();

                DataSnapshot categories = dataSnapshot.child("categories");

                int currentScoreWorth = Integer.parseInt(questions.get(round-2).getText().toString().replaceAll(",", ""));
                Log.d("SCORE WORTH", "" + currentScoreWorth);
                if (currentScoreWorth < 1000) {
                    category = "0";
                }
                else if (currentScoreWorth < 15000)
                    category = "1,000";
                else if (currentScoreWorth < 1000000)
                    category = "15,000";
                else if (currentScoreWorth == 1000000)
                    category = "1,000,000";

                Question question = categories.child(category).child("question"+round).getValue(Question.class);

                if (answer.equals(question.getAnswer())) {
                    currentScoreWorth = Integer.parseInt(questions.get(round-1).getText().toString().replaceAll(",", ""));
                    Log.d("SCORE WORTH", "" + currentScoreWorth);
                    if (currentScoreWorth < 1000) {
                        category = "0";
                    }
                    else if (currentScoreWorth < 15000)
                        category = "1,000";
                    else if (currentScoreWorth < 1000000)
                        category = "15,000";
                    else if (currentScoreWorth == 1000000)
                        category = "1,000,000";

                    question = categories.child(category).child("question"+round).getValue(Question.class);

                    questionTextView.setText(question.getQuestion());
                    aButton.setText(question.getOptionA());
                    bButton.setText(question.getOptionB());
                    cButton.setText(question.getOptionC());
                    dButton.setText(question.getOptionD());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void chooseB (View view) {
            answer = bButton.getText().toString();
        if (doubleDipActivated)
            secondAnswer = bButton.getText().toString();

        databaseQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                answer = bButton.getText().toString();

                DataSnapshot categories = dataSnapshot.child("categories");

                int currentScoreWorth = Integer.parseInt(questions.get(round-2).getText().toString().replaceAll(",", ""));
                Log.d("SCORE WORTH", "" + currentScoreWorth);
                if (currentScoreWorth < 1000) {
                    category = "0";
                }
                else if (currentScoreWorth < 15000)
                    category = "1,000";
                else if (currentScoreWorth < 1000000)
                    category = "15,000";
                else if (currentScoreWorth == 1000000)
                    category = "1,000,000";

                Question question = categories.child(category).child("question"+round).getValue(Question.class);

                if (answer.equals(question.getAnswer())) {
                    currentScoreWorth = Integer.parseInt(questions.get(round-1).getText().toString().replaceAll(",", ""));
                    Log.d("SCORE WORTH", "" + currentScoreWorth);
                    if (currentScoreWorth < 1000) {
                        category = "0";
                    }
                    else if (currentScoreWorth < 15000)
                        category = "1,000";
                    else if (currentScoreWorth < 1000000)
                        category = "15,000";
                    else if (currentScoreWorth == 1000000)
                        category = "1,000,000";

                    question = categories.child(category).child("question"+round).getValue(Question.class);

                    questionTextView.setText(question.getQuestion());
                    aButton.setText(question.getOptionA());
                    bButton.setText(question.getOptionB());
                    cButton.setText(question.getOptionC());
                    dButton.setText(question.getOptionD());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void chooseC (View view) {
            answer = cButton.getText().toString();
         if (doubleDipActivated)
            secondAnswer = cButton.getText().toString();


    }

    public void chooseD (View view) {
            answer = dButton.getText().toString();
         if (doubleDipActivated)
            secondAnswer = dButton.getText().toString();

        
    }

    // LIFELINES
    public void use5050(View view) {
        if (!used5050) {
            bButton.setText("");
            cButton.setText("");
            used5050 = true;
        }
        lifeline5050.setImageResource(R.drawable.yokelli);
    }

    public void useDoubleDip(View view) {
        if (!usedDoubleDip) {
            doubleDipActivated = true;
            usedDoubleDip = true;
        }
        lifelineDoubleDip.setImageResource(R.drawable.yokseyirci);
    }

    public void useNextQuestion(View view) {
        if (!usedNextQuestion) {

            thread.interrupt(); // i know nag move lang siya to the next prize question but eh

            usedNextQuestion = true;
        }
        lifelineNextQuestion.setImageResource(R.drawable.yoktelefon);
    }
}

