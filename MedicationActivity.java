package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MedicationActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextMedication;
    EditText mTextTime;
    EditText mTextDosage;
    EditText mTextHowLong;
    Button mButtonSubmit;
    Button mButtonView;
    ImageView mImageViewBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        db = new DatabaseHelper(this);
        mTextMedication= (EditText)findViewById(R.id.editText_Medication);
        mTextTime = (EditText)findViewById(R.id.editTextTime);
        mTextDosage = (EditText)findViewById(R.id.editText_Dosage);
        mTextHowLong = (EditText)findViewById(R.id.editText_HowLong);
        mButtonSubmit = (Button)findViewById(R.id.button_SubmitMedication);
        mButtonView = (Button)findViewById(R.id.button_ViewMedications);
        mImageViewBack = (ImageView)findViewById(R.id.imageView_backButtonMedication);
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MedToHomeIntent = new Intent(MedicationActivity.this, HomeActivity.class);
                startActivity(MedToHomeIntent);
            }
        });
        mButtonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent viewMedsIntent = new Intent(MedicationActivity.this, ViewMedicationActivity.class);
                startActivity(viewMedsIntent);
            }

        });
        mButtonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String med = mTextMedication.getText().toString().trim();
                String timeToTake = mTextTime.getText().toString().trim();
                String dosage = mTextDosage.getText().toString().trim();
                String howLong = mTextHowLong.getText().toString().trim();

                boolean val = db.addMedication(med, timeToTake, dosage, howLong);
                if (val) {
                    Toast.makeText(MedicationActivity.this, "Medication Information Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MedicationActivity.this, "Error Inputting Data. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
            });

        }
}