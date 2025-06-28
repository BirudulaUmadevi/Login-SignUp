package com.example.login_signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText;
    Button logoutBtn;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);

        welcomeText = findViewById(R.id.textWelcome);
        logoutBtn = findViewById(R.id.buttonLogout);

        String email = sharedPreferences.getString("email", "User");
        welcomeText.setText("Welcome, " + email + "!");

        logoutBtn.setOnClickListener(v -> {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        });
    }
}