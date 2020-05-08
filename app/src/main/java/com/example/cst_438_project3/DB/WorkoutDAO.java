package com.example.cst_438_project3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.cst_438_project3.Objects.Workout;
import java.util.List;

@Dao
public interface WorkoutDAO {

    @Update
    void update(Workout workout);

    @Insert
    void insert(Workout workout);

    @Delete
    void delete(Workout workout);

    @Query("select * from workoutTable")
    List<Workout> getAllWorkouts();

    //pull a workout from the DB by ID
    @Query("select * from workoutTable where WorkoutID = :WorkoutID")
    Workout getWorkoutByID(int WorkoutID);
}
