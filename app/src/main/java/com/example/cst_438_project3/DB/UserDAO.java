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
    void update(User users);

    @Insert
    void insert(User users);

    @Delete
    void delete(User user);

    @Query("select * from userTable")
    List<User> getAllUsers();

    @Query("select * from userTable where username = :username")
    User getUserByName(String username);

    //pull a user from the DB by ID
    @Query("select * from userTable where UserID = :UserID")
    User getUserByID(int UserID);


    @Query("select * from userTable where username = :username and password= :password")
    User login(String username, String password);

    @Query("UPDATE userTable SET password = :password where UserID = :UserID")
    void updatePassword(String password, int UserID);

}


