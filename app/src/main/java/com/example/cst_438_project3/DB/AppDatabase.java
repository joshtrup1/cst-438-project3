package com.example.cst_438_project3.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cst_438_project3.Objects.User;

@Database(entities = {User.class}, version =1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //User
    public static final String UName ="db-user";
    public static final String USER_TABLE ="user";

    public abstract UserDAO getUserDao();
}

