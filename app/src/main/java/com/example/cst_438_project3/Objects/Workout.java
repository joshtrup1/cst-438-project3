package com.example.cst_438_project3.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cst_438_project3.DB.AppDatabase;

@Entity(tableName = AppDatabase.WORKOUT_TABLE)
public class Workout {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int WorkoutID;


    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private int reps;


    public Workout() {}

    @Ignore
    public Workout(String name, String description, int reps ) {
        this.name = name;
        this.description = description;
        this.reps = reps;
    }

    public int getWorkoutID() {
        return WorkoutID;
    }

    public void setWorkoutID(int workoutID) {
        WorkoutID = workoutID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reps=" + reps +
                '}';
    }
}
