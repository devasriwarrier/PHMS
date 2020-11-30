package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MonitoringSystemActivity extends AppCompatActivity {
    Button mImageViewBackMonitoringSystem;
    Button mButtonMedYes;
    Button mButtonMedNo;
    EditText mEditTextTimeTaken;
    Button mButtonMedView;
    EditText mEditTextnotes;
    EditText mEditTextmessage;
    Button mButtonMedSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_system);

        ImageView mImageViewBackMonitoringSystem = (ImageView) findViewById(R.id.mImageViewBackMonitoringSystem);
        mImageViewBackMonitoringSystem.setOnClickListener(view -> {
            Intent MonViewToHome = new Intent( MonitoringSystemActivity.this, HomeActivity.class);
            //startActivity(MedViewToHome);
        });


    }
}