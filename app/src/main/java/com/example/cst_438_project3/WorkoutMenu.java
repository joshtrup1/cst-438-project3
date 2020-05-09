package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.Objects.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkoutMenu extends AppCompatActivity {

    TextView mainDisplay;
    FloatingActionButton addWorkoutButton;
    FloatingActionButton editWorkoutButton;
    FloatingActionButton deleteWorkoutButton;

    String selectedDate;

//    private ArrayList<Workout> workouts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_menu);
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
        User user = AppDatabase.getAppDatabase(WorkoutMenu.this).
                userDAO().getUserByID(user_id.get());

        //retrieve the date
        selectedDate = getIntent().getStringExtra("selectedDate");

        //retrieve all the users workouts with the selected date
        //push to array

        mainDisplay = findViewById(R.id.mainWorkoutDisplay);
        mainDisplay.setMovementMethod(new ScrollingMovementMethod());
        addWorkoutButton = findViewById(R.id.addWorkout);
        editWorkoutButton = findViewById(R.id.editWorkout);
        deleteWorkoutButton = findViewById(R.id.deleteWorkout);
        refreshDisplay();

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorkout();
            }
        });

        editWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editWorkout();
            }
        });

        deleteWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteWorkout();
            }
        });
    }
    private void refreshDisplay(){
        //append workouts in the array to the textView
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(selectedDate + '\n');
        mainDisplay.setText(stringBuilder.toString());
    }

    void addWorkout(){
//        Intent i = new Intent(this, addWorkoutActivity.class);
//        i.putExtra("userId", u.getID());
//        startActivity(i);
//        finish();
    }

    void editWorkout(){
//        retrieve the workout number form the textView
//        Intent i = new Intent(this, editWorkoutActivity.class);
//        i.putExtra("userId", u.getID());

//        pass on the workout

//        startActivity(i);
//        finish();
    }

    void deleteWorkout(){
//        retrieve the workout number form the textView
//        Intent i = new Intent(this, deleteWorkoutActivity.class);
//        i.putExtra("userId", u.getID());

//        pass on the workout

//        startActivity(i);
//        finish();
    }
}
