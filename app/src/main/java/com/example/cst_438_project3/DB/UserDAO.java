package com.example.cst_438_project3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_438_project3.Objects.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Update
    void update(User... users);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User... user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE )
    List<User> getAllUsers();

    //pull a specific item from the database
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE  + " WHERE mUsername = :username")
    User getUserWithUsername(String username);
}


