package com.example.loginregistration;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMedicationActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private final ArrayList<String> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medication);

        ImageView mImageViewBackMedicationViewer = (ImageView) findViewById(R.id.imageView_backButtonMedicationView);

        db = new DatabaseHelper(this);
        viewMedication();

        ListView listView = (ListView) findViewById(R.id.medication_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.item_view, R.id.itemTextView, listItems);
        listView.setAdapter(arrayAdapter);

        mImageViewBackMedicationViewer.setOnClickListener(view -> {
            Intent MedViewToHome = new Intent( ViewMedicationActivity.this, HomeActivity.class);
            startActivity(MedViewToHome);
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton_AddMedication);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Add Medication", Snackbar.LENGTH_LONG);
            Intent addMedicationIntent = new Intent(ViewMedicationActivity.this, MedicationActivity.class);
            startActivity(addMedicationIntent);
        });
    }

    private void viewMedication() {
        Cursor cursor = db.viewMedication();
        int medTotal = cursor.getCount();

        String[] totalMeds = new String[cursor.getCount()];
        String[] medName = new String[cursor.getCount()];
        String[] medTime = new String[cursor.getCount()];
        String[] medDosage = new String[cursor.getCount()];
        String[] medLength = new String[cursor.getCount()];

        if (medTotal == 0) {
            Intent addMedicationIntent = new Intent(ViewMedicationActivity.this, MedicationActivity.class);
            startActivity(addMedicationIntent);
            Toast.makeText(ViewMedicationActivity.this,"No medications to View",Toast.LENGTH_SHORT).show();
        } else {
           /* for(int i = 0; i < medTotal; i ++){
                medName[i] = cursor.getString(1);
                medTime[i] = cursor.getString(2);
                medDosage[i] = cursor.getString(3);
                medLength[i] = cursor.getString(4);
                //Toast.makeText(this,"Medication: " + medName[i] + "\n" + "Time to Take: " + medTime[i] + "\n" + "Dosage: " +  medDosage[i] + "\n" + "How Long: " + medLength[i] + "\n", Toast.LENGTH_LONG).show();
                cursor.moveToNext();
            } */
            try {
                do {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Medication Name: ").append(cursor.getString(1)).append("\n");
                    sb.append("Time to Take: ").append(cursor.getString(2)).append("\n");
                    sb.append("Dosage: ").append(cursor.getString(3)).append("\n");
                    sb.append("How Long: ").append(cursor.getString(4)).append("\n");

                    listItems.add(sb.toString());
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }
    }
}