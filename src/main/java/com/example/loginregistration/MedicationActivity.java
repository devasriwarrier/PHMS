package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MedicationActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextMedication;
    EditText mTextTime;
    EditText mTextDosage;
    EditText mTextHowLong;
    Button mButtonSubmit;
    Button mButtonEdit;


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
        mButtonEdit = (Button)findViewById(R.id.button_EditMedications);

    }
}