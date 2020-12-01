package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChangePasswordActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText mEditTextCurrentPassword = (EditText) findViewById(R.id.editText_CurrentPassword);
        EditText mEditTextNewPassword = (EditText) findViewById(R.id.editText_NewPassword);
        EditText mEditTextConfirmNewPassword = (EditText) findViewById(R.id.editText_NewPasswordConfirm);
        Button mButtonChangePassword = (Button) findViewById(R.id.button_ChangePassword);
        ImageView mImageViewBackButtonCP = (ImageView) findViewById(R.id.imageView_backButtonChangePassword);

        mImageViewBackButtonCP.setOnClickListener(v -> {
            Intent changePasswordToSettings = new Intent(ChangePasswordActivity.this,SettingsActivity.class);
            startActivity(changePasswordToSettings);
        });

        mButtonChangePassword.setOnClickListener(v -> {

        });
    }
}