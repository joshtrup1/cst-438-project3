package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.UserDAO;

public class ProfileActivity extends AppCompatActivity {

    TextView usernameTextView;
    UserDAO mUserDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = findViewById(R.id.username);

        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.UName)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        //setText for username
        //usernameTextView.setText(username);
    }




}
