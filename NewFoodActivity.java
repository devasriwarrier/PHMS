package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.getInteger;

public class NewFoodActivity extends AppCompatActivity {

    Button dButtonConfirm;
    EditText dEditServe;
    EditText dEditCal;
    EditText dEditName;
    ImageView dImageViewBack;

    int calorie_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        Intent mIntent = getIntent();
        calorie_counter = mIntent.getIntExtra("Calorie_ID",0);

        dEditServe = (EditText) findViewById(R.id.serving2);
        dEditCal = (EditText) findViewById(R.id.Calories);
        dEditName = (EditText) findViewById(R.id.Undisc);

        dButtonConfirm = (Button)findViewById(R.id.button2);

        dButtonConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt( dEditServe.getText().toString() );
                int cal = Integer.parseInt( dEditCal.getText().toString() );


                int new_value = cal*amount;
                calorie_counter+=new_value;
                String again = Integer.toString(new_value);

                Toast.makeText(NewFoodActivity.this, again + " Calories", Toast.LENGTH_SHORT).show();

                Intent LoginIntent = new Intent(NewFoodActivity.this, DietActivity.class);
                startActivity(LoginIntent);
            }
        });

        dImageViewBack = (ImageView)findViewById(R.id.imageView_backButtonDiet);
        dImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewToDietIntent = new Intent(NewFoodActivity.this, DietActivity.class);
                startActivity(NewToDietIntent);
            }
        });

    }
}