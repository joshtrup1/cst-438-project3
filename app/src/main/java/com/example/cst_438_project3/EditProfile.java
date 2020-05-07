package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.Objects.User;

import java.util.concurrent.atomic.AtomicInteger;

public class EditProfile extends AppCompatActivity {

    TextView usernameTextView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Log.d("Profile", "onCreate Edit Profile");

        //Get extras from Profile Activity
        final AtomicInteger user_id = new AtomicInteger(-1);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                user_id.set(extras.getInt("user_id"));
            }
        } else {
            user_id.set((int) savedInstanceState.getSerializable("user_id"));
        }

        user = AppDatabase.getAppDatabase(EditProfile.this).
                userDAO().getUserByID(user_id.get());

        //Set Username in usernameTextView
        usernameTextView = findViewById(R.id.username);
        usernameTextView.setText(user.getUsername());

        final EditText old_password = findViewById(R.id.old_password);
        final EditText new_password = findViewById(R.id.new_password);
        final EditText confirm_password = findViewById(R.id.confirm_password);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePaswword(old_password.getText().toString(), new_password.getText().toString(), confirm_password.getText().toString(), user);
            }
        });

    }

    public void changePaswword(String o_pw, String n_pw, String c_pw, User user) {
        if(!user.getPassword().equals(o_pw)) {
            //wrong password
            //alert user
            Log.d("Password", "password " + user.getPassword() + " old password " + o_pw);
            utils.display_toast(getApplicationContext(), "Password is incorrect.");
            return;
        }

        boolean validPassword = false;
        if(checkValid(o_pw) && checkValid(n_pw)) {
            validPassword = true;
        }

        if(validPassword) {
            if(n_pw.equals(c_pw)) {
                //New Passwords match
                //Change password
                Log.d("Password", "setting new password " + n_pw + " " + c_pw);
                user.setPassword(n_pw);

                AppDatabase.getAppDatabase(EditProfile.this).userDAO().updatePassword(n_pw, user.getUserID());
                Log.d("Password", "new password " + user.getPassword());

                //Redirect to Login
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            else {
                //New passwords don't match
                Log.d("Password", "mismatch " + n_pw + " " + c_pw);
                utils.display_toast(getApplicationContext(), "New password does not match.");
            }
        }
        else {
            //not a valid password
            //alert "password must contain a capital and a number"
            Log.d("Password", "invalid password " + n_pw + " " + c_pw);
            utils.display_toast(getApplicationContext(), "Invalid Password");
        }

    }

    public boolean checkValid(String n_pw) {
        boolean upper = false;
        boolean digit = false;

        for(int i = 0; i < n_pw.length(); i++){
            char c = n_pw.charAt(i);

            if ('0' <= c && c <= '9') {
                digit = true;
            }
            else if('A' <= c && c <= 'Z'){
                upper=true;
            }
        }
        return upper && digit;
    }
}
