package com.example.whowantstobeamillionaire;

import android.widget.Toast;

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
    static DatabaseReference databasePlayers;

    public static Player player;

    public static void initializeQuestions() {
        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");


        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot categories = dataSnapshot.child("categories");

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

    public static void registerPlayer(final String username, final String password) {
        databasePlayers = FirebaseDatabase.getInstance().getReference("Players");

        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exist = false;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if (postSnapshot.getKey().equals(username))
                        exist = true;
                }

                if (!exist)
                    databasePlayers.child(username).setValue(new Player(username, password));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void loginPlayer(final String username) {
        databasePlayers = FirebaseDatabase.getInstance().getReference("Players");

        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                    if (postSnapshot.getKey().equals(username))
                        player = postSnapshot.getValue(Player.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
