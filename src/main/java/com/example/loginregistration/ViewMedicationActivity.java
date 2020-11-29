package com.example.loginregistration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMedicationActivity extends AppCompatActivity {


    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView medicationList;
    String totalMeds[], medName[], medTime[], medDosage[], medLength[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        db = new DatabaseHelper(this);
        viewMedication();
        listItem = new ArrayList<>();
        medicationList = (ListView)findViewById(R.id.medication_list);

        medicationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String text = medicationList.getItemAtPosition(i).toString();
                Toast.makeText(ViewMedicationActivity.this,"" + text, Toast.LENGTH_SHORT).show();
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton_AddMedication);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Medication", Snackbar.LENGTH_LONG);
                Intent addMedicationIntent = new Intent(ViewMedicationActivity.this, MedicationActivity.class);
                startActivity(addMedicationIntent);
            }
        });
    }

    private void viewMedication() {
        Cursor cursor = db.viewMedication();
        totalMeds = new String[cursor.getCount()];
        medName = new String[cursor.getCount()];
        medTime = new String[cursor.getCount()];
        medDosage = new String[cursor.getCount()];
        medLength = new String[cursor.getCount()];
        cursor.moveToFirst();
        int size = totalMeds.length;
        if(size == 0){
            Intent addMedicationIntent = new Intent(ViewMedicationActivity.this, MedicationActivity.class);
            startActivity(addMedicationIntent);
            //Toast.makeText(ViewMedicationActivity.this,"No medications to View",Toast.LENGTH_SHORT).show();
        }
        else{
            for(int i = 0; i < totalMeds.length; i ++){
                medName[i] = cursor.getString(1);
                medTime[i] = cursor.getString(2);
                medDosage[i] = cursor.getString(3);
                medLength[i] = cursor.getString(4);
                Toast.makeText(this,"Medication: " + medName[i] + "\n" + "Time to Take: " + medTime[i] + "\n" + "Dosage: " +  medDosage[i] + "\n" + "How Long: " + medLength[i] + "\n", Toast.LENGTH_LONG).show();
                cursor.moveToNext();
            }
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Medication Name: " + cursor.getString(1) + "\n");
                buffer.append("Time to Take: " + cursor.getString(2) + "\n");
               buffer.append("Dosage: " + cursor.getString(3) + "\n");
               buffer.append("How Long: " + cursor.getString(4) + "\n");

                //listItem.add(cursor.getString(1));
            }
            Toast.makeText(getApplicationContext(), "All Medication: \n" + buffer.toString(), Toast.LENGTH_LONG).show();
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listItem);
            medicationList.setAdapter(adapter);
        }

    }
}