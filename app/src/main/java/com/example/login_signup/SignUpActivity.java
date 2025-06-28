package com.example.login_signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText newemail, newpassword,conpwd;
    Button signupBtn;

    TextView login;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);

        newemail = findViewById(R.id.editEmail);
        newpassword = findViewById(R.id.editPassword);
        conpwd =findViewById(R.id.editConfirmPassword);
        signupBtn = findViewById(R.id.buttonSignup);
        login=findViewById(R.id.textLogin);

        login.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        });

        signupBtn.setOnClickListener(v -> signupUser());
    }

    private void signupUser() {
        String email = newemail.getText().toString().trim();
        String password = newpassword.getText().toString().trim();
        String confirmpwd = conpwd.getText().toString().trim();

        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";


        if (email.isEmpty() || password.isEmpty() || confirmpwd.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.matches(passwordPattern)) {
            Toast.makeText(this, "Password must be at least 8 characters and include uppercase, lowercase, number, and special character", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmpwd)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPreferences.edit()
                .putString("email", email)
                .putString("password", password)
                .apply();

        Toast.makeText(this, "Signup Successful", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

}