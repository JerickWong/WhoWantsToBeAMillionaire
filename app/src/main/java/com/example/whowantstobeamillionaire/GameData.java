package com.example.whowantstobeamillionaire;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameData {
    public static ArrayList<Question> question0 = new ArrayList<>();
    public static ArrayList<Question> question1000 = new ArrayList<>();
    public static ArrayList<Question> question15k = new ArrayList<>();
    public static ArrayList<Question> question1M = new ArrayList<>();

    static DatabaseReference databaseQuestions;
    static DatabaseReference databasePlayers;

    public static Player player;
    public static boolean registered, loggedin;

    public static void initializeDBQuestions() {
        Log.e("nullpointer?", "before retrieving database questions");
        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");

        Log.e("nullpointer?", "after retrieving database questions");
        databaseQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("nullpointer?", "before retrieving database categories");
                DataSnapshot categories = dataSnapshot.child("categories");
                Log.e("nullpointer?", "after retrieving database questions");
                for (DataSnapshot postSnapShot : categories.getChildren()) {
                    if (postSnapShot.getKey().equals("0")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = new Question(snapshot.getValue(Question.class));
                            question0.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("1,000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = new Question(snapshot.getValue(Question.class));
                            question1000.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("15,000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = new Question(snapshot.getValue(Question.class));
                            question15k.add(question);
                        }
                    } else if (postSnapShot.getKey().equals("1,000,0000")) {
                        for (DataSnapshot snapshot: postSnapShot.getChildren()) {
                            Question question = new Question(snapshot.getValue(Question.class));
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

    public static boolean registerPlayer(final String username, final String password) {
        databasePlayers = FirebaseDatabase.getInstance().getReference("players");

        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exist = false;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if (postSnapshot.getKey().equals(username))
                        exist = true;
                }

                if (!exist) {
                    databasePlayers.child(username).setValue(new Player(username, password));
                    registered = true;
                } else
                    registered = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return registered;
    }

    public static void loginPlayer(final String username) {
        databasePlayers = FirebaseDatabase.getInstance().getReference("players");

        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loggedin = false;
                Log.e("BEFORE ITERATION", "BEFORE FOR LOOP");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.e("INSIDE FOR LOOP", "DITO DAPAT KA");
                    if (postSnapshot.getKey().equals(username)) {
                        player = postSnapshot.getValue(Player.class);
                        loggedin = true;
                        Log.e("LOGIN", "LOGIN SUCCESSFULLY");
                    }
                }

                Log.e("LOGGEDIN VALUE", "" + loggedin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
