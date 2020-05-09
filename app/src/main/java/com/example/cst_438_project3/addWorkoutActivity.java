package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cst_438_project3.Objects.Workout;

import androidx.room.Room;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.WorkoutDAO;

public class addWorkoutActivity extends AppCompatActivity {

    private WorkoutDAO mWorkoutDao;

    private Button submitButton;
    private TextView tow;
    private TextView start;
    private TextView end;
    private int userID;
    private TextView reps;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_layout);

        Intent intent = getIntent();
        userID = intent.getIntExtra("userID",1);

        mWorkoutDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.UName)
                .allowMainThreadQueries()
                .build()
                .getWorkoutDAO();

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
        String workout = tow.getText().toString();
        String r = reps.getText().toString(); //reps
        String st = start.getText().toString();
        String finish = end.getText().toString();

        if(workout.length() != 0 && r.length() != 0 && st.length() != 0 && finish.length() != 0){
            Workout w = new Workout(workout,r, st, finish, userID);
            mWorkoutDao.insert(w);
            finish();
        } else{
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show();
        }



    }




}
