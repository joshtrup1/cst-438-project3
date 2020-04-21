package com.example.cst_438_project3.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cst_438_project3.DB.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int UserID;


    @NonNull
    private String username;
    @NonNull
    private String password;


    public User() {}

    @Ignore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    @Override
    public String toString() {
        return
                "Username: " + username + " || Password:" + password + '\n';
    }
}
