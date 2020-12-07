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

public class HomeActivity extends AppCompatActivity {

    ImageButton mButtonMedication;
    ImageButton mButtonSettings;
    ImageButton mButtonPersonalInfo;
    ImageButton mButtonDiet;
    ImageButton mButtonComm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonPersonalInfo = (ImageButton) findViewById(R.id.imageButton_PersonalInfo);
        mButtonMedication = (ImageButton) findViewById(R.id.imageButton_Medicine);
        mButtonSettings = (ImageButton) findViewById(R.id.imageButton_Settings);
        mButtonDiet = (ImageButton) findViewById(R.id.imageButton_Diet);
        mButtonComm = (ImageButton) findViewById(R.id.imageButton_Communications);

        mButtonMedication.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent medicationIntent = new Intent(HomeActivity.this, ViewMedicationActivity.class);
                startActivity(medicationIntent);
            }
        });

        mButtonPersonalInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent personalInfoIntent = new Intent(HomeActivity.this, PersonalInfoActivity.class);
                startActivity(personalInfoIntent);
            }

        });

        mButtonDiet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent dietInfoIntent = new Intent(HomeActivity.this, DietActivity.class);
                startActivity(dietInfoIntent);
            }

        });

        mButtonComm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent comInfoIntent = new Intent(HomeActivity.this, CommunicationsActivity.class);
                startActivity(comInfoIntent);
            }

        });
    }
}