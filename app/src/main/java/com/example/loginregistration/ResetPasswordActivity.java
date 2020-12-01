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

public class ResetPasswordActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new DatabaseHelper(this);
        ImageView mImageViewBackButtonRP = findViewById(R.id.imageView_backButtonResetPassword);
        EditText mEditTextPasswordReset = findViewById(R.id.editTextPasswordReset);
        EditText mEditTextRePasswordReset = findViewById(R.id.editTextRePasswordReset);
        Button mButtonConfirmReset = findViewById(R.id.buttonResetPasswordConfirm);

        Intent userIntent = getIntent();
        String username = userIntent.getStringExtra("username");
        Intent emailIntent = getIntent();
        String email = emailIntent.getStringExtra("email");

        mButtonConfirmReset.setOnClickListener(v -> {
            String password = mEditTextPasswordReset.getText().toString();
            String confirmPassword = mEditTextRePasswordReset.getText().toString();
            if (password.equals(confirmPassword)) {

                if (db.updatePassword(username, email, password)) {
                    Intent ResetPasswordToLogin = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                    startActivity(ResetPasswordToLogin);
                    Toast.makeText(ResetPasswordActivity.this,"Password Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResetPasswordActivity.this,"Password Not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });

        mImageViewBackButtonRP.setOnClickListener(v -> {
            Intent ResetToForgot = new Intent(ResetPasswordActivity.this,ForgotPasswordActivity.class);
            startActivity(ResetToForgot);
        });
    }
}