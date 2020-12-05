package com.example.loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MonitoringSystemActivity extends AppCompatActivity {
    Button mImageViewBackMonitoringSystem;
    Button mButtonMedYes;
    Button mButtonMedNo;
    EditText mEditTextTimeTaken;
    Button mButtonMedView;
    EditText mEditTextnotes;
    EditText mEditTextmessage;
    Button mButtonMedSend;
    Button btnSetNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_system);

        ImageView mImageViewBackMonitoringSystem = (ImageView) findViewById(R.id.mImageViewBackMonitoringSystem);
        mImageViewBackMonitoringSystem.setOnClickListener(view -> {
            Intent MonViewToHome = new Intent( MonitoringSystemActivity.this, HomeActivity.class);
            startActivity(MonViewToHome);
        });


        Button btnSetNotification = (Button) findViewById(R.id.btnSetNotification);
        btnSetNotification.setOnClickListener(view -> {
            Intent MontoNotif = new Intent( MonitoringSystemActivity.this, NotificationActivity.class);
            startActivity(MontoNotif);
        });

        Button mButtonMedYes = (Button) findViewById(R.id.mButtonMedYes);
        mButtonMedYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Good Work", "Nice job! Keep it up.");
            }

        });

        Button mButtonMedNo = (Button) findViewById(R.id.mButtonMedNo);
        mButtonMedNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Oh no!", "Let's tell your Doctor, Pharmacist, and Next of Kin.");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendSMS();
                    }
                }, 2000);

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{  "warrier.devasri@yahoo.com, warrier.dev@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "NOTICE OF LATE OR MISSED MEDICATION");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello respected recipients. I am emailing to notify that I have missed my medication. Please add this important notice to my file.");


                emailIntent.setType("message/rfc822");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(emailIntent);
                    }
                }, 4000);
            }

        });


    }

public void showMessage(String title, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}


protected void sendSMS() {
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage("2145296259", null, "NOTICE: MHP user medication missed/taken late! This is reminder to take it on time until dosage is complete!", null, null);
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
    SmsManager smsManager2 = SmsManager.getDefault();
    smsManager.sendTextMessage("2145852004", null, "NOTICE: MHP user medication missed/taken late!", null, null);
}}


