package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity<textView> extends AppCompatActivity {
    private EditText mTextUsername;
    private EditText mTextPassword;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.username);
        mTextPassword = (EditText) findViewById(R.id.password);
        Button mButtonLogin = (Button) findViewById(R.id.login);
        TextView mTextViewRegister = (TextView) findViewById(R.id.register);
        TextView mTextViewForgotPassword = (TextView) findViewById(R.id.textView_forgotPassword);

        mTextViewRegister.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        mTextViewForgotPassword.setOnClickListener(v -> {
            Intent loginToForgotPassword = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
            startActivity(loginToForgotPassword);
        });

        mButtonLogin.setOnClickListener(view -> {
            String user = mTextUsername.getText().toString().trim();
            String pass = mTextPassword.getText().toString().trim();
            boolean res = db.checkUserExists(user, pass);
            if (res) {
                Intent HomePage = new Intent(LoginActivity.this, HomeActivity.class);
                DatabaseHelper.currentUser = user;
                db.setCurrentUserID(user);
                startActivity(HomePage);
            } else {
                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}