package com.example.loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PersonalInfoActivity extends AppCompatActivity {

    EditText mTextUserName;
    EditText mTextName;
    EditText mTextGender;
    EditText mTextWeight;
    EditText mTextHeight;
    EditText mTextAge;
    EditText mTextdocName;
    EditText mTextdocEmail;
    EditText mTextpharmName;
    EditText mTextpharmEmail;
    EditText mTextkinName;
    EditText mTextkinEmail;
    EditText mTextvisitDate;
    Button mButtonPersonalInfo;
    Button mButtonUpdatePersonalInfo;
    Button mButtonValidatePersonalInfo;

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        myDb = new DatabaseHelper(this);
        mTextUserName = (EditText)findViewById(R.id.mTextUserName);
        mTextName = (EditText)findViewById(R.id.mTextName);
        mTextWeight = (EditText)findViewById(R.id.mTextWeight);
        mTextHeight = (EditText)findViewById(R.id.mTextHeight);
        mTextAge = (EditText)findViewById(R.id.mTextAge);
        mTextGender = (EditText)findViewById(R.id.mTextGender);
        mTextdocName = (EditText)findViewById(R.id.mTextdocName);
        mTextdocEmail = (EditText)findViewById(R.id.mTextdocEmail);
        mTextpharmName = (EditText)findViewById(R.id.mTextpharmName);
        mTextpharmEmail = (EditText)findViewById(R.id.mTextpharmEmail);
        mTextkinName = (EditText)findViewById(R.id.mTextkinName);
        mTextkinEmail = (EditText)findViewById(R.id.mTextkinEmail);
        mTextvisitDate = (EditText)findViewById(R.id.mTextvisitDate);
        mButtonPersonalInfo = (Button) findViewById(R.id.mButtonPersonalInfo);
        mButtonUpdatePersonalInfo = (Button) findViewById(R.id.mButtonUpdatePersonalInfo);
        mButtonValidatePersonalInfo = (Button)findViewById(R.id.mButtonValidatePersonalInfo);
        AddPersonalData();
        ViewPersonalData();
        UpdatePersonalData();
    }

    public void UpdatePersonalData() {
        mButtonUpdatePersonalInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updatePersonalInfo(mTextUserName.getText().toString(),
                                mTextName.getText().toString(),
                                mTextWeight.getText().toString(),
                                mTextHeight.getText().toString(),
                                mTextAge.getText().toString(),
                                mTextGender.getText().toString(),
                                mTextdocName.getText().toString(),
                                mTextdocEmail.getText().toString(),
                                mTextpharmName.getText().toString(),
                                mTextpharmEmail.getText().toString(),
                                mTextkinName.getText().toString(),
                                mTextkinEmail.getText().toString(),
                                mTextvisitDate.getText().toString());
                        if(isUpdate == true) {
                            Toast.makeText(PersonalInfoActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(PersonalInfoActivity.this, "Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


    public void ViewPersonalData(){
        mButtonValidatePersonalInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor result = myDb.viewPersonalInfo();
                        if(result.getCount() == 0){
                            //message
                            showMessage("Error", "Nothing found.");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(result.moveToNext()) {
                            //buffer.append("ID: "+ result.getString(0)+"\n");
                            buffer.append("Username: "+ result.getString(1)+"\n");
                            buffer.append("Name: "+ result.getString(2)+"\n");
                            buffer.append("Weight: "+ result.getString(3)+"\n");
                            buffer.append("Height: "+ result.getString(4)+"\n");
                            buffer.append("Age: "+ result.getString(5)+"\n");
                            buffer.append("Gender: "+ result.getString(6)+"\n");
                            buffer.append("Doctor Name: "+ result.getString(7)+"\n");
                            buffer.append("Doctor Email: "+ result.getString(8)+"\n");
                            buffer.append("Pharmacist Name: "+ result.getString(9)+"\n");
                            buffer.append("Pharmacist Email: "+ result.getString(10)+"\n");
                            buffer.append("Next of Kin Name: "+ result.getString(11)+"\n");
                            buffer.append("Next of Kin Email: "+ result.getString(12)+"\n");
                            buffer.append("Visit Date: "+ result.getString(13)+"\n\n");
                        }
                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void AddPersonalData() {
        mButtonPersonalInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.addPersonalInfo(mTextUserName.getText().toString(),
                                mTextName.getText().toString(),
                                mTextWeight.getText().toString(),
                                mTextHeight.getText().toString(),
                                mTextAge.getText().toString(),
                                mTextGender.getText().toString(),
                                mTextdocName.getText().toString(),
                                mTextdocEmail.getText().toString(),
                                mTextpharmName.getText().toString(),
                                mTextpharmEmail.getText().toString(),
                                mTextkinName.getText().toString(),
                                mTextkinEmail.getText().toString(),
                                mTextvisitDate.getText().toString());
                        if(isInserted = true) {
                            Toast.makeText(PersonalInfoActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(PersonalInfoActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


}
