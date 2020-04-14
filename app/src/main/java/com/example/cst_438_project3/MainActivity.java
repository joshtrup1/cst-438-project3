package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.Objects.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mainDisplay;

    EditText username;
    EditText password;

    Button submit;
    Button clear;

    UserDAO mUserDAO;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDisplay = findViewById(R.id.mainGymLogDisplay);
        mainDisplay.setMovementMethod(new ScrollingMovementMethod());
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        submit = findViewById(R.id.mainSubmitButton);
        clear = findViewById(R.id.mainClearButton);

        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.UName)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        refreshDisplay();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterInfo();
                refreshDisplay();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDatabase();
                refreshDisplay();
            }
        });
    }

    private void refreshDisplay(){
        users = mUserDAO.getAllUsers();

        if(!users.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();

            for(User u: users){
                stringBuilder.append(u.toString());
            }

            mainDisplay.setText(stringBuilder.toString());
        } else {
            mainDisplay.setText("No accounts in the database");
        }
    }

    private void enterInfo(){
        String newUsername = username.getText().toString();
        String newPassword = password.getText().toString();

        mUserDAO.insert(new User(newUsername, newPassword));
    }

    private void clearDatabase(){
        if(! users.isEmpty()){
            //clear the DB
            for(User u: users){
                mUserDAO.delete(u);
            }
        }
    }
}
