package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageView mImageViewChangePassword = findViewById(R.id.imageView_changePassword);

        mImageViewChangePassword.setOnClickListener(v -> {
            Intent SettingsToChangePassword = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
            startActivity(SettingsToChangePassword);
        });

        ImageView mImageViewLogOut = findViewById(R.id.imageView_logOut);

        mImageViewLogOut.setOnClickListener(v -> {
            Intent settingsLogOut = new Intent(SettingsActivity.this, LoginActivity.class);
            DatabaseHelper.currentUserID = -1;
            DatabaseHelper.currentUser = "";
            startActivity(settingsLogOut);
        });

        ImageView mImageViewBackButton = findViewById(R.id.imageView_backButtonSettings);

        mImageViewBackButton.setOnClickListener(v -> {
            Intent settingsToHome = new Intent(SettingsActivity.this, HomeActivity.class);
            startActivity(settingsToHome);
        });
    }
}