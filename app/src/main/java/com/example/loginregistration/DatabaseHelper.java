package com.example.loginregistration;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userInfo.db";
    public static final String USER_INFO = "registeruser";
    public static final String COL_1 = "ID"; //all columns in DB
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "Name";
    public static final String COL_5 = "Email"; //TODO: Jason remember to handle this
    public static final String COL_6 = "Address"; //TODO: Jason remember to handle this
    public static final String COL_7 = "Phone"; //TODO: Jason remember to handle this
    public static final String COL_8 = "Security_Question"; //TODO: Jason remember to handle this
    public static final String COL_9 = "Security_Answer"; //TODO: Jason remember to handle this
    public static final String MEDICATION = "medication";
    public static final String COL_10 = "Medication_ID";
    public static final String COL_11 = "Medication_Name";
    public static final String COL_12 = "Time_To_Take";
    public static final String COL_13 = "How_Much";
    public static final String COL_14 = "How_Long";
    public static final String COL_15 = "User_ID";
    public static final String TABLE_NAME_PERSONAL_INFO = "personalInfo";
    public static final String COL_16 = "ID"; //all columns in DB
    public static final String COL_17 = "WEIGHT"; //TODO: Devasri remember to handle this in Monitoring System
    public static final String COL_18 = "HEIGHT";
    public static final String COL_19 = "AGE";
    public static final String COL_20 = "GENDER";
    public static final String COL_21 = "DOCTOR_NAME"; //TODO: Devasri remember to handle this in Monitoring System
    public static final String COL_22 = "DOCTOR_EMAIL"; //TODO: Devasri remember to handle this in Monitoring System
    public static final String COL_23 = "PHARM_NAME";
    public static final String COL_24 = "PHARM_EMAIL";
    public static final String COL_25 = "KIN_NAME";
    public static final String COL_26 = "KIN_EMAIL";
    public static final String COL_27 = "VISIT_DATE";
    public static final String COL_28 = "User_ID";
    public static String currentUser = "";
    public static int currentUserID = -1;
    //TODO: Might need to add variables for "diet"

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT UNIQUE, Password TEXT, Name TEXT, Email TEXT, Address TEXT, Phone TEXT, Security_Question TEXT, Security_Answer TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE medication (Medication_ID INTEGER PRIMARY KEY AUTOINCREMENT, Medication_Name TEXT, Time_To_Take TEXT, How_Much TEXT, How_Long TEXT, User_ID INTEGER, FOREIGN KEY(User_ID) REFERENCES registeruser(ID) )");
        sqLiteDatabase.execSQL("CREATE TABLE personalInfo (ID INTEGER PRIMARY KEY AUTOINCREMENT, WEIGHT INT, HEIGHT INT, AGE INT, GENDER TEXT, DOCTOR_NAME TEXT, DOCTOR_EMAIL TEXT, PHARM_NAME TEXT, PHARM_EMAIL TEXT, KIN_NAME TEXT, KIN_EMAIL TEXT, VISIT_DATE TEXT, User_ID INTEGER, FOREIGN KEY(USER_ID) REFERENCES registeruser(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_INFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEDICATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PERSONAL_INFO);
        onCreate(sqLiteDatabase);
    }

    public boolean addUser(String user, String password, String name, String email, String address, String phone, String securityQuestion, String securityAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, name);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, address);
        contentValues.put(COL_7, phone);
        contentValues.put(COL_8, securityQuestion);
        contentValues.put(COL_9, securityAnswer);

        long res = db.insert(USER_INFO, null, contentValues);
        db.close();

        return res != -1;
    }

    public boolean checkUserExists(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {COL_1};
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(USER_INFO, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public boolean addMedication(String medName, String timeToTake, String howMuch, String howLong) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11, medName);
        contentValues.put(COL_12, timeToTake);
        contentValues.put(COL_13, howMuch);
        contentValues.put(COL_14, howLong);
        contentValues.put(COL_15, currentUserID);

        long res = db.insert(MEDICATION, null, contentValues);
        db.close();
        viewMedication();

        return res != -1;
    }

    public void setCurrentUserID(String user){
        SQLiteDatabase read = getReadableDatabase();

        String[] columns = {COL_1};
        String selection = COL_2 + "=?";
        String[] loggedIn = {user};

        Cursor cursor = read.query(USER_INFO, columns, selection, loggedIn,null, null, null);
        cursor.moveToFirst();
        currentUserID = cursor.getInt(0);
        cursor.close();
    }

    public Cursor viewMedication(){
        SQLiteDatabase read = this.getReadableDatabase();

        String[] columns = {COL_10, COL_11, COL_12, COL_13, COL_14, COL_15};
        String selection = COL_15 + "=?";
        String[] loggedIn = {String.valueOf(currentUserID)};

        Cursor cursor = read.query(MEDICATION, columns, selection, loggedIn, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public boolean addPersonalInfo(String weight, String height, String age, String gender, String docname, String docemail, String pharmname, String pharmemail, String kinname, String kinemail, String visitdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase read = getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_2 + "=?";
        String[] loggedIn = {currentUser};
        //int id = getIntent().getIntExtra();
        //Log.d("User:",loggedIn[0]);
        Cursor cursor = read.query(USER_INFO,columns,selection,loggedIn,null,null,null);

        cursor.moveToFirst();
        currentUserID = cursor.getInt(0);


        contentValues.put("WEIGHT", weight);
        contentValues.put("HEIGHT", height);
        contentValues.put("AGE", age);
        contentValues.put("GENDER", gender);
        contentValues.put("DOCTOR_NAME", docname);
        contentValues.put("DOCTOR_EMAIL", docemail);
        contentValues.put("PHARM_NAME", pharmname);
        contentValues.put("PHARM_EMAIL", pharmemail);
        contentValues.put("KIN_NAME", kinname);
        contentValues.put("KIN_EMAIL", kinemail);
        contentValues.put("VISIT_DATE", visitdate);
        contentValues.put("User_ID", currentUserID);

        long result = db.insert("personalInfo", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
        //db.close();
    }
    public Cursor viewPersonalInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME_PERSONAL_INFO, null);
        return result;
    }
    public boolean updatePersonalInfo(String weight, String height, String age, String gender, String docname, String docemail, String pharmname, String pharmemail, String kinname, String kinemail, String visitdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase read = getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_2 + "=?";
        String[] loggedIn = {currentUser};
        //int id = getIntent().getIntExtra();
        //Log.d("User:",loggedIn[0]);
        Cursor cursor = read.query(USER_INFO,columns,selection,loggedIn,null,null,null);

        cursor.moveToFirst();
        currentUserID = cursor.getInt(0);

        //contentValues.put("ID", id);
        contentValues.put("WEIGHT", weight);
        contentValues.put("HEIGHT", height);
        contentValues.put("AGE", age);
        contentValues.put("GENDER", gender);
        contentValues.put("DOCTOR_NAME", docname);
        contentValues.put("DOCTOR_EMAIL", docemail);
        contentValues.put("PHARM_NAME", pharmname);
        contentValues.put("PHARM_EMAIL", pharmemail);
        contentValues.put("KIN_NAME", kinname);
        contentValues.put("KIN_EMAIL", kinemail);
        contentValues.put("VISIT_DATE", visitdate);
        contentValues.put("User_ID", currentUserID);
        cursor.close();
        db.update(TABLE_NAME_PERSONAL_INFO, contentValues, "User_ID = ?", new String[] {String.valueOf(currentUserID)} );
        return true;
    }
}
