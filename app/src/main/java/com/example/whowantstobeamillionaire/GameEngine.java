package com.example.whowantstobeamillionaire;

import android.graphics.Canvas;
import android.renderscript.Sampler;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    ArrayList<Question> questions;
    static int gameState;
    Random random; //determines where the answer will be palced.
    int playerScore; //or net worth
    boolean lifeline_5050; //remove 2 wrong answers
    boolean lifeline_doubleDip; //allow for 2 answers
    boolean lifeline_switchQuestion; //switch the question

    DatabaseReference databaseQuestions;

    public int absoluteValue(int n){
        if (n < 0)
            return -n;
        return n;
    }

    public GameEngine(){
        questions = new ArrayList<>();
        /* game states:
        * 0 - game not started
        * 1 - game TECHNICALLY started; waiting for answer; timer running
        * 2 - correct answer; proceed to next question; timer stops
        * 3 - game over; timer stops; game stops */
        gameState = 0;
        random = new Random();
        playerScore = 0;
        lifeline_5050 = true;
        lifeline_doubleDip = true;
        lifeline_switchQuestion = true;

        DatabaseQuestions.initialize();
        initializeQuestions();
    }

    public void initializeQuestions() {
        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");

        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questions.clear();

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                    Question question = postSnapShot.getValue(Question.class);
                    questions.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void nextQuestion(Canvas canvas){
        drawBackground(canvas);
        drawQuestionBox(canvas);
        drawFirstChoice(canvas);
        drawSecondChoice(canvas);
        drawThirdChoice(canvas);
        drawFourthChoice(canvas);
    }

    public void drawBackground(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), AppConstants.getBitmapBank().getBackgroundWidth(), AppConstants.getBitmapBank().getBackgroundHeight(), null);
    }

    public void drawQuestionBox(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getQuestionBox(), AppConstants.getBitmapBank().getxPosQ(), AppConstants.getBitmapBank().getyPosQ(), null);
    }

    public void drawFirstChoice(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getQuestionBox(), AppConstants.getBitmapBank().getxPos1(), AppConstants.getBitmapBank().getyPos1(), null);
    }

    public void drawSecondChoice(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getQuestionBox(), AppConstants.getBitmapBank().getxPos2(), AppConstants.getBitmapBank().getyPos2(), null);
    }

    public void drawThirdChoice(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getQuestionBox(), AppConstants.getBitmapBank().getxPos3(), AppConstants.getBitmapBank().getyPos3(), null);
    }

    public void drawFourthChoice(Canvas canvas){
        canvas.drawBitmap(AppConstants.getBitmapBank().getQuestionBox(), AppConstants.getBitmapBank().getxPos4(), AppConstants.getBitmapBank().getyPos4(), null);
    }

    public void startGame() {

    }
}
