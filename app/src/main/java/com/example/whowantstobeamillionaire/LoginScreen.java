package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    Button registerBtn;
    EditText usernameTextView, passwordTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        usernameTextView = (EditText) findViewById(R.id.usernameTextView);
        passwordTextView = (EditText) findViewById(R.id.passwordTextView);

    }

    public void login(View v){
        if (usernameTextView.getText().toString().matches("") ||
                passwordTextView.getText().toString().matches("")) {
            Toast.makeText(this, "Enter a username/password", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            finish();
        }

        // FIREBASE LOGIN CHECKER PA
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
