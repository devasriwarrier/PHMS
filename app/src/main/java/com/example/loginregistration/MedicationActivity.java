package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MedicationActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText mTextMedication;
    private EditText mTextTime;
    private EditText mTextDosage;
    private EditText mTextHowLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new DatabaseHelper(this);
        mTextMedication= (EditText)findViewById(R.id.editText_Medication);
        mTextTime = (EditText)findViewById(R.id.editTextTime);
        mTextDosage = (EditText)findViewById(R.id.editText_Dosage);
        mTextHowLong = (EditText)findViewById(R.id.editText_HowLong);
        Button mButtonSubmit = (Button) findViewById(R.id.button_SubmitMedication);
        Button mButtonView = (Button) findViewById(R.id.button_ViewMedications);
        ImageView mImageViewBack = (ImageView) findViewById(R.id.imageView_backButtonMedication);

        mImageViewBack.setOnClickListener(view -> {
            Cursor cursor = db.viewMedication();
            int medTotal = cursor.getCount();

            if (medTotal == 0) {
                Intent MedToHome = new Intent(MedicationActivity.this,HomeActivity.class);
                startActivity(MedToHome);
            } else {
                Intent MedToViewIntent = new Intent(MedicationActivity.this, ViewMedicationActivity.class);
                startActivity(MedToViewIntent);
            }
        });

        mButtonView.setOnClickListener(view -> {
            Intent viewMedsIntent = new Intent(MedicationActivity.this, ViewMedicationActivity.class);
            startActivity(viewMedsIntent);
        });

        mButtonSubmit.setOnClickListener(view -> {
            String med = mTextMedication.getText().toString().trim();
            String timeToTake = mTextTime.getText().toString().trim();
            String dosage = mTextDosage.getText().toString().trim();
            String howLong = mTextHowLong.getText().toString().trim();


            if (TextUtils.isEmpty(med)) {
                mTextMedication.setError("Medication name required!");
            } else if (TextUtils.isEmpty(timeToTake)) {
                mTextTime.setError("Time to take medication required!");
            } else if (TextUtils.isEmpty(dosage)) {
                mTextDosage.setError("Dosage is required!");
            } else if (TextUtils.isEmpty(howLong)) {
                mTextHowLong.setError("Length of time needed to take medication is required!");
            } else {
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