package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WorkoutMenu extends AppCompatActivity {

    TextView mainDisplay;
    FloatingActionButton addWorkoutButton;
    FloatingActionButton editWorkoutButton;
    FloatingActionButton deleteWorkoutButton;

//    private ArrayList<Workout> workouts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_menu);

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
