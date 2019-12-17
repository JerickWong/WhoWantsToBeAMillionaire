package com.example.whowantstobeamillionaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    Button registerBtn;
    EditText usernameTextView, passwordTextView;
    DatabaseReference databasePlayers;
    Context  context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        usernameTextView = (EditText) findViewById(R.id.usernameTextView);
        passwordTextView = (EditText) findViewById(R.id.passwordTextView);

        databasePlayers = FirebaseDatabase.getInstance().getReference("players");



    }

    public void login(View v){
        if (usernameTextView.getText().toString().matches("") ||
                passwordTextView.getText().toString().matches("")) {
            Toast.makeText(this, "Enter a username/password", Toast.LENGTH_SHORT).show();
        }
        else {
            GameData.loginPlayer(usernameTextView.getText().toString());

            if (GameData.loggedin) {
                Toast.makeText(this, "Username and password did not match!", Toast.LENGTH_SHORT).show();
            } else {


                databasePlayers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean loggedin = false;
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            if (postSnapshot.getKey().equals(usernameTextView.getText().toString())) {
                                loggedin = true;
                            }
                        }

                        if (loggedin) {
                            Toast.makeText(LoginScreen.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MainMenu.class);
                            startActivity(intent);
                            finish();
                            
                        } else {
                            Toast.makeText(LoginScreen.this, "Username and Password did not match!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }

        // FIREBASE LOGIN CHECKER PA
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
