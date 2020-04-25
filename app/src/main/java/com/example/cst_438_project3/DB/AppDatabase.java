package com.example.cst_438_project3.DB;


import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.Workout;
import com.example.cst_438_project3.DB.WorkoutDAO;

@Database(entities = {User.class, Workout.class}, version =1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //User
    public static final String UName ="db-user";
    public static final String USER_TABLE ="user";
    public static final String WORKOUT_TABLE = "workout";

    public abstract UserDAO getUserDao();
    public abstract WorkoutDAO getWorkoutDAO();
}

