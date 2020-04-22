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
import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.Objects.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LoginActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            //calls the onclick method
            //will ask username and password on page
            @Override
            public void onClick(View v) {

                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                String name = username.getText().toString();
                String pw = password.getText().toString();



                UserDAO userDAO = AppDatabase.getAppDatabase(LoginActivity.this).userDAO();
                User user = userDAO.login(name, pw);

                if (user == null) {
                    // unsuccessful login
                    utils.display_toast(getApplicationContext(), "Username or password is incorrect.");
                } else {
                    // successful login

                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("user_id", user.getUserID());
                    finish();
                    startActivity(intent);
                }
            }


        });
    }

}
