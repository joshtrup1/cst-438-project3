package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.WorkoutDAO;
import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.WeightLog;
import com.example.cst_438_project3.Objects.Workout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkoutMenu extends AppCompatActivity {

    TextView mainDisplay;
    EditText mWorkoutID;
    FloatingActionButton addWorkoutButton;
    FloatingActionButton editWorkoutButton;
    FloatingActionButton deleteWorkoutButton;

    String selectedDate;

    Integer userID;
    Integer workoutID;

    List<Workout> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_menu);

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

        userID = user_id.get();
        Log.d("calendar", "user Id in WorkoutMenu (onCreate): " + userID);

        //retrieve the date
        selectedDate = getIntent().getStringExtra("selectedDate");


        //retrieve all the users workouts with the selected date
        workouts = AppDatabase.getAppDatabase(WorkoutMenu.this).
                WorkoutDAO().getAllWorkouts();

        mainDisplay = findViewById(R.id.mainWorkoutDisplay);
        mainDisplay.setMovementMethod(new ScrollingMovementMethod());
        addWorkoutButton = findViewById(R.id.addWorkout);
        editWorkoutButton = findViewById(R.id.editWorkout);
        deleteWorkoutButton = findViewById(R.id.deleteWorkout);
        mWorkoutID = findViewById(R.id.workoutID);
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
                refreshDisplay();
            }
        });
    }
    private void refreshDisplay(){
        boolean found = false;
        if(! workouts.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for(Workout w: workouts){
                if(w.getUserID() == userID){
                    if(w.getDate().equals(selectedDate)){
                        stringBuilder.append(w.toString());
                        found = true;
                    }
                }
            }
            if(!found){
                stringBuilder.append("No Workouts Available");
            }
            mainDisplay.setText(stringBuilder.toString());
        }
    }

    void addWorkout(){
        Intent i = new Intent(this, addWorkoutActivity.class);
        i.putExtra("user_id", userID);
        i.putExtra("selectedDate", selectedDate);
        Log.d("calendar", "user Id in WorkoutMenu (addWorkout function): " + userID);
        startActivity(i);
        finish();
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
        //find workout in the db
        int wID = Integer.valueOf(mWorkoutID.getText().toString());
        for(Workout w: workouts){
            if(w.getWorkoutID() == wID){
                WorkoutDAO workout_dao = AppDatabase.getAppDatabase(WorkoutMenu.this).WorkoutDAO();
                workout_dao.delete(w);
            }
        }
    }
}
