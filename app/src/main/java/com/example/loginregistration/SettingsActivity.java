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

        ImageButton mImageButtonLogOut = (ImageButton) findViewById(R.id.imageButton_logOut);

        mImageButtonLogOut.setOnClickListener(v -> {
            Intent settingsLogOut = new Intent(SettingsActivity.this, LoginActivity.class);
            DatabaseHelper.currentUserID = -1;
            DatabaseHelper.currentUser = "";
            startActivity(settingsLogOut);
        });

        ImageView mImageViewBackButton = (ImageView) findViewById(R.id.imageView_backButtonSettings);

        mImageViewBackButton.setOnClickListener(v -> {
            Intent settingsToHome = new Intent(SettingsActivity.this, HomeActivity.class);
            startActivity(settingsToHome);
        });
    }
}