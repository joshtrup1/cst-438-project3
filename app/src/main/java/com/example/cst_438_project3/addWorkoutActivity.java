package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst_438_project3.DB.UserDAO;
import com.example.cst_438_project3.DB.WeightLogDAO;
import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.WeightLog;
import com.example.cst_438_project3.Objects.Workout;

import androidx.room.Room;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.WorkoutDAO;

import java.util.concurrent.atomic.AtomicInteger;

public class addWorkoutActivity extends AppCompatActivity {

    private WorkoutDAO mWorkoutDao;

    private Button submitButton;
    private TextView tow;
    private TextView start;
    private TextView end;
    private int userID;
    private TextView reps;
    User user;
    String selectedDate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_layout);

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
        User user = AppDatabase.getAppDatabase(addWorkoutActivity.this).
                userDAO().getUserByID(user_id.get());

        id = user_id.get();

        tow = findViewById(R.id.typeofworkout);
        reps = findViewById(R.id.rep);
        start = findViewById(R.id.start_time);
        end = findViewById(R.id.end_time);


        submitButton = findViewById(R.id.addworkoutButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addWorkout();
            }
        });
    }

    private void addWorkout() {
        String type_of_workout = tow.getText().toString();
        String r = reps.getText().toString(); //reps
        String st = start.getText().toString();
        String finish = end.getText().toString();
        String date = getIntent().getStringExtra("selectedDate");

        if(type_of_workout.length() != 0 && r.length() != 0 && st.length() != 0 && finish.length() != 0){

            Workout newWorkout = new Workout(type_of_workout,r,st,finish,date,id);

            WorkoutDAO workout_dao = AppDatabase.getAppDatabase(addWorkoutActivity.this).WorkoutDAO();
            workout_dao.insert(newWorkout);
            finish();

        } else{
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show();
        }



    }




}
