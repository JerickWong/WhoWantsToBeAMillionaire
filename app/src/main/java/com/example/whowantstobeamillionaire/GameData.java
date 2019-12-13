package com.example.whowantstobeamillionaire;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameData {
    public static ArrayList<Question> question0;
    public static ArrayList<Question> question1000;
    public static ArrayList<Question> question15k;
    public static ArrayList<Question> question1M;

    static DatabaseReference databaseQuestions;

    public static void initializeQuestions() {
        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");


        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot categories = dataSnapshot.child("Category");

                for (DataSnapshot postSnapShot : categories.getChildren()) {
                    if (postSnapShot.getKey().equals("0")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = snapshot.getValue(Question.class);
                            question0.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("1,000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = snapshot.getValue(Question.class);
                            question1000.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("15,000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = snapshot.getValue(Question.class);
                            question15k.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("1,000,0000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = snapshot.getValue(Question.class);
                            question1M.add(question);
                        }
                    }
                }
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
