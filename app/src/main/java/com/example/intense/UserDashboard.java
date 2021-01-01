package com.example.intense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDashboard extends AppCompatActivity {

    TextView userEmailTv;

    SharedPreferences pref;
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        pref =  getSharedPreferences("userInfoPrefs", MODE_PRIVATE);

        // Hook
        userEmailTv = findViewById(R.id.userEmailTextView);

        email = pref.getString("userEmail", "user@email.com");
        userEmailTv.setText(email);
    }

    public void logoutUser(View view) {
        pref.edit().clear().apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}