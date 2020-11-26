package com.example.loginregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="userInfo.db";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1 = "ID"; //all columns in DB
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "Name";
    public static final String COL_5 = "Email"; //TODO: Jason remember to handle this
    public static final String COL_6 = "Address"; //TODO: Jason remember to handle this
    public static final String COL_7 = "Phone"; //TODO: Jason remember to handle this
    public static final String COL_8 = "Security_Question"; //TODO: Jason remember to handle this
    public static final String COL_9 = "Security_Answer"; //TODO: Jason remember to handle this
    //TODO: Might need to add variables for "diet"

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Name TEXT, Email TEXT, Address TEXT, Phone TEXT, Security_Question TEXT, Security_Answer TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean addUser(String user, String password, String name, String email, String address, String phone, String securityQuestion, String securityAnswer){
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
        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if(res == -1)
            return false;
        else
            return true;
    }

    public boolean checkUserExists(String username, String password){
        String[] columns = { COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection =  COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password };
        Cursor cursor = db.query(TABLE_NAME, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if( count > 0)
            return true;
        else
            return false;
    }
}
