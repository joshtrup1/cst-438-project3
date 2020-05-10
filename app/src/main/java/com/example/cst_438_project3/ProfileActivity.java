//TODO: I need to pass the user ID to the next activity
package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.Objects.User;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class ProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public static final String selectedDate = "com.example.cst_438_project3.selectedDate";

    TextView usernameTextView;

    Integer username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Get extras from Login
        final AtomicInteger user_id = new AtomicInteger(-1);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                user_id.set(extras.getInt("user_id"));
            }
        } else {
            user_id.set((int) savedInstanceState.getSerializable("user_id"));
        }

        //Get current logged in user
        User user = AppDatabase.getAppDatabase(ProfileActivity.this).
                userDAO().getUserByID(user_id.get());

        username = user.getUserID();

        //setText for username
        usernameTextView = findViewById(R.id.username);
        usernameTextView.setText(user.getUsername());

        //calendar
        Button button = (Button) findViewById(R.id.calendar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //graph
        Button button2 = (Button) findViewById(R.id.weightProgress);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightTracker();
            }
        });

        //weight log
        Button button3 = (Button) findViewById(R.id.weightLog);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightLog();
            }
        });

        //step counter
        Button step_counter = findViewById(R.id.stepCounter);
        step_counter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call the login activity
                Log.d("ProfileActivity", "onClick for login called");
                Intent intent = new Intent(ProfileActivity.this, StepCounter.class);
                startActivity(intent);

            }
        });

        //Change password
        Button changePW = (Button) findViewById(R.id.change_password);
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfile.class);
                intent.putExtra("user_id", username);
                finish();
                Log.d("Profile", "start Edit Profile Intent");
                startActivity(intent);
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        Log.d("profileActivity", "onClick for create account called");
        //send Date to the workoutMenu activity
        Intent intent = new Intent(this, WorkoutMenu.class);
        intent.putExtra("selectedDate", currentDateString);
        intent.putExtra("user_id", username);
        Log.d("calendar", "user id: " + username);
        startActivity(intent);

    }

    public void openWeightTracker(){
        Intent intent = new Intent(this, WeightTracker.class);
        intent.putExtra("user_id", username);
        startActivity(intent);
    }

    public void openWeightLog(){
        Intent intent = new Intent(ProfileActivity.this, WeightLogActivity.class);
        intent.putExtra("user_id", username);
        startActivity(intent);
    }

}
