package com.example.intense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;

public class MainActivity extends AppCompatActivity {

    EditText emailEt, passwordEt;
    MaterialCheckBox rememberMeCb;

    SharedPreferences pref;
    private String email = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Remove the notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pref = getSharedPreferences("userInfoPrefs",MODE_PRIVATE);

        // When this activity is about to be launch we need to check if its opened before or not
        if (isAlreadyLoggedIn()) {
            redirectingToDashboard();
        }

        setContentView(R.layout.activity_main);

        // Hook
        emailEt = findViewById(R.id.emailEditText);
        passwordEt = findViewById(R.id.passwordEditText);
        rememberMeCb = findViewById(R.id.rememberMe);

        rememberMeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                   rememberUser();
                } else if (!buttonView.isChecked()) {
                    pref.edit().clear().apply();
                }
            }
        });
    }

    private boolean isAlreadyLoggedIn() {
        return  pref.getBoolean("isLoggedIn",false);
    }

    private void rememberUser() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public void loginUser(View view) {
        email = emailEt.getText().toString();
        password = passwordEt.getText().toString();

        // Saving user email for entire app
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userEmail", email);
        editor.apply();

        redirectingToDashboard();
    }

    private void redirectingToDashboard() {
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);
        finish();
    }

}