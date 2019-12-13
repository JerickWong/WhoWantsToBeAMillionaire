package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText, confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
    }

    public void register(View view) {
        if (usernameEditText.getText().toString().matches(""))
            Toast.makeText(this, "Enter a username!", Toast.LENGTH_SHORT).show();
        else if (passwordEditText.getText().toString().matches(""))
            Toast.makeText(this, "Enter a password!", Toast.LENGTH_SHORT).show();
        else if (confirmPasswordEditText.getText().toString().matches(""))
            Toast.makeText(this, "Confirm your password!", Toast.LENGTH_SHORT).show();
        else if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString()))
            Toast.makeText(this, "Password did not match!", Toast.LENGTH_SHORT).show();
        else {
            // MAY CHECKING PA IF USERNAME EXISTS FROM FIREBASE
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            finish();
        }
    }
}
