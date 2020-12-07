package com.example.loginregistration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.CALL_PHONE;


public class CommunicationsActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private EditText editTextMessage;

    ImageView dImageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communications);

        ActivityCompat.requestPermissions(CommunicationsActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
        editTextMessage = findViewById(R.id.editText);
        editTextNumber = findViewById(R.id.editTextNumber);

        dImageViewBack = (ImageView)findViewById(R.id.imageView_backButtonDiet);
        dImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DietToHomeIntent = new Intent(CommunicationsActivity.this, HomeActivity.class);
                startActivity(DietToHomeIntent);
            }
        });
    }

    public void sendSMS(View view){

        String message = editTextMessage.getText().toString();
        String number = editTextNumber.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, message, null, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void CallButton(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CommunicationsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                return;
            }
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + editTextNumber.getText().toString()));
        startActivity(callIntent);
    }
}





