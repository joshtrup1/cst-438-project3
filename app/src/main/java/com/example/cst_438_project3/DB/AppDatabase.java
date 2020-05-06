package com.example.cst_438_project3.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.Workout;

@Database(entities = {User.class, Workout.class}, version =1, exportSchema = false)



public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase instance;

    //daos
    public abstract UserDAO userDAO();
    public abstract WorkoutDAO workoutDAO();
    //tables
    public static final String USER_TABLE = "userTable";
    public static final String WORKOUT_TABLE = "workoutTable";





    public static AppDatabase getAppDatabase(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "FitnessApp Database") // database name
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }




}

