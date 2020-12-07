package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    EditText mTextWeight;
    Button mButtonUpdateWeightInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        myDb = new DatabaseHelper(this);

        mTextWeight = (EditText)findViewById(R.id.mTextWeight);
        mButtonUpdateWeightInfo = (Button) findViewById(R.id.mButtonUpdatePersonalInfo);

        UpdateWeightData();
    }

    public void UpdateWeightData() {
        mButtonUpdateWeightInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateWeightInfo(mTextWeight.getText().toString());

                        if(isUpdate) {
                            Toast.makeText(WeightActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(WeightActivity.this, "Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}