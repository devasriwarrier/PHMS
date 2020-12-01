package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText mTextUsername;
    private EditText mTextPassword;
    private EditText mTextConfirmPassword;
    private EditText mTextEmail;
    private EditText mTextName;
    private EditText mTextAddress;
    private EditText mTextPhone;
    private EditText mTextSecurityQuestion;
    private EditText mTextSecurityAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextConfirmPassword = (EditText) findViewById(R.id.edittext_rePassword);
        mTextName = (EditText) findViewById(R.id.edittext_name);
        mTextEmail = (EditText) findViewById(R.id.edittext_email);
        mTextAddress = (EditText) findViewById(R.id.edittext_address);
        mTextPhone = (EditText) findViewById(R.id.edittext_phone);
        mTextSecurityQuestion = (EditText) findViewById(R.id.edittext_securityQuestion);
        mTextSecurityAnswer = (EditText) findViewById(R.id.edittext_securityAnswer);
        Button mButtonRegister = (Button) findViewById(R.id.buttonRegister);
        TextView mTextViewLogin = (TextView) findViewById(R.id.textView_login);

        mTextViewLogin.setOnClickListener(view -> {
            Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(LoginIntent);
        });

        mButtonRegister.setOnClickListener(view -> {
            String user = mTextUsername.getText().toString().trim();
            String pass = mTextPassword.getText().toString().trim();
            String name = mTextName.getText().toString().trim();
            String email = mTextEmail.getText().toString().trim();
            String address = mTextAddress.getText().toString().trim();
            String phone = mTextPhone.getText().toString().trim();
            String securityQuestion = mTextSecurityQuestion.getText().toString().trim();
            String securityAnswer = mTextSecurityAnswer.getText().toString().trim();
            String confirmPass = mTextConfirmPassword.getText().toString().trim();

            if (pass.equals(confirmPass)) {
                boolean val = db.addUser(user,pass,name,email,address,phone,securityQuestion,securityAnswer);

                if (val) {
                    Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLogin);
                } else {
                    Toast.makeText(RegisterActivity.this, "Error Registering. Please Try Again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}