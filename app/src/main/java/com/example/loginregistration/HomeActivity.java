package com.example.loginregistration;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;

import java.util.Set;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton mButtonPersonalInfo = (ImageButton) findViewById(R.id.imageButton_PersonalInfo);
        ImageButton mButtonMedication = (ImageButton) findViewById(R.id.imageButton_Medicine);
        ImageButton mButtonSettings = (ImageButton) findViewById(R.id.imageButton_Settings);
        ImageButton mButtonMonitoringSystem = (ImageButton) findViewById(R.id.mButtonMonitoringSystem);

        mButtonMonitoringSystem.setOnClickListener(view -> {
            Intent monitoringSystemIntent = new Intent(HomeActivity.this, MonitoringSystemActivity.class);
            startActivity(monitoringSystemIntent);
        });

        mButtonMedication.setOnClickListener(view -> {
            Intent medicationIntent = new Intent(HomeActivity.this, ViewMedicationActivity.class);
            startActivity(medicationIntent);
        });

        mButtonPersonalInfo.setOnClickListener(view -> {
            Intent personalInfoIntent = new Intent(HomeActivity.this, PersonalInfoActivity.class);
            startActivity(personalInfoIntent);
        });

        mButtonSettings.setOnClickListener(v -> {
            Intent SettingsIntent = new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(SettingsIntent);
        });
    }
}