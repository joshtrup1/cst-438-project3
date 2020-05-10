package com.example.cst_438_project3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.Objects.User;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //button to save the new account
        Button saveAccount = findViewById(R.id.save_button);
        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userName = findViewById(R.id.username);
                EditText passWord = findViewById(R.id.password);

                User user = AppDatabase.getAppDatabase(CreateAccountActivity.this).
                        userDAO().getUserByName(userName.getText().toString());

                //check if user exists already
                if (user == null) {

                    String username = userName.getText().toString();
                    String password = passWord.getText().toString();

                    //prompts the user that username or password is invalid
                    if(!checkvalid(username) || !checkvalid(password)) {
                        TextView msg = findViewById(R.id.notify);
                        msg.setText(" Invalid username or password.");
                        return;
                    }

                    // add the new user account into the database
                    User newUser = new User(username, password);

                    UserDAO user_dao = AppDatabase.getAppDatabase(CreateAccountActivity.this).userDAO();
                    user_dao.insert(newUser);


                    // inform user that a new account has been created successfully
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder.setTitle("Account successfully created.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();



                } else {
                    // username already exists.
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder.setMessage("Username not available");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }








            }
        });
    }


    //check that username and password contain at least one uppercase letter and one number
    private boolean checkvalid(String S){
        boolean upper = false;
        boolean digit = false;

        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);

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

