package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.Objects.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create_account_button = findViewById(R.id.create_account);
        create_account_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the create account activity
                Log.d("mainActivity", "onClick for create account called");
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the login activity
                Log.d("mainActivity", "onClick for login called");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });



        Button exit_button = findViewById(R.id.exit);
        exit_button.setOnClickListener(new View.OnClickListener(){
            // call to exit the application
            @Override
            public void onClick(View v) {
                Log.d("Exit", "onClick for exit called");
                finish();  // make it actually close app from anywhere !!

            }
        });





    }
}

