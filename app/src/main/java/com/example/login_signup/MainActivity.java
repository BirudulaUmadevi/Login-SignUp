package com.example.login_signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText oldemail, oldpassword;
    Button loginBtn;
    TextView signupLink;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);

        // Auto-login if already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

        oldemail = findViewById(R.id.editEmail);
        oldpassword = findViewById(R.id.editPassword);
        loginBtn = findViewById(R.id.buttonLogin);
        signupLink = findViewById(R.id.textSignup);

        loginBtn.setOnClickListener(v -> loginUser());

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() {
        String email = oldemail.getText().toString();
        String password = oldpassword.getText().toString();

        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return;
        }

        if (email.equals(savedEmail) && password.equals(savedPassword)) {
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_LONG).show();
        }

    }
}