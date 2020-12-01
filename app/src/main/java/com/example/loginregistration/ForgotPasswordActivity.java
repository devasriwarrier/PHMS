package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new DatabaseHelper(this);
        ImageView mImageViewBackButtonFP = findViewById(R.id.imageView_backButtonFP);
        EditText mEditTextFPUsername = findViewById(R.id.editText_usernameFP);
        EditText mEditTextFPEmail = findViewById(R.id.editText_emailFP);
        Button mButtonResetPassword = findViewById(R.id.buttonResetPassword);

        mButtonResetPassword.setOnClickListener(v -> {
            String user = mEditTextFPUsername.getText().toString();
            String email = mEditTextFPEmail.getText().toString();

            boolean checkEmail = db.checkUserEmailExists(user, email);
            if (checkEmail) {
                Intent goToResetPassword = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                goToResetPassword.putExtra("username", user);
                goToResetPassword.putExtra("email", email);
                startActivity(goToResetPassword);
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Username or email do not match any records", Toast.LENGTH_SHORT).show();
            }
        });

        mImageViewBackButtonFP.setOnClickListener(v -> {
            Intent ForgotPasswordToLogin = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(ForgotPasswordToLogin);
        });
    }
}