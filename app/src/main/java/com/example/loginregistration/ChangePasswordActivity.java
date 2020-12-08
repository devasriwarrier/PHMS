package com.example.loginregistration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DatabaseHelper db = new DatabaseHelper(this);
        EditText mEditTextCurrentPassword = findViewById(R.id.editText_CurrentPassword);
        EditText mEditTextNewPassword = findViewById(R.id.editText_NewPassword);
        EditText mEditTextConfirmNewPassword = findViewById(R.id.editText_NewPasswordConfirm);
        Button mButtonChangePassword = findViewById(R.id.button_ChangePassword);
        ImageView mImageViewBackButtonCP = findViewById(R.id.imageView_backButtonChangePassword);

        mImageViewBackButtonCP.setOnClickListener(v -> {
            Intent changePasswordToSettings = new Intent(ChangePasswordActivity.this,SettingsActivity.class);
            startActivity(changePasswordToSettings);
        });

        mButtonChangePassword.setOnClickListener(v -> {
            String currentPassword = mEditTextCurrentPassword.getText().toString().trim();
            String newPassword = mEditTextNewPassword.getText().toString().trim();
            String confirmNewPassword = mEditTextConfirmNewPassword.getText().toString().trim();
            System.out.println("Current Password: " + currentPassword);
            System.out.println("New Password: " + newPassword);
            System.out.println("Confirm Password: " + confirmNewPassword);

            if (db.checkPasswordsMatch(currentPassword)) {
                if (newPassword.equals(confirmNewPassword)) {
                    if (db.updatePassword(DatabaseHelper.currentUser, DatabaseHelper.currentUserEmail, newPassword)) {
                        Intent ChangePasswordToSettings = new Intent(ChangePasswordActivity.this, SettingsActivity.class);
                        Toast.makeText(ChangePasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(ChangePasswordToSettings);

                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Password Not Changed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mEditTextNewPassword.setError("Passwords do not match");
                    mEditTextConfirmNewPassword.setError("Passwords do not match");
                }
            } else {
                mEditTextCurrentPassword.setError("Password is incorrect");
            }
        });
    }
}