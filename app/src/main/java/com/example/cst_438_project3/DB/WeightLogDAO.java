package com.example.cst_438_project3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.WeightLog;

import java.util.List;

@Dao
public interface WeightLogDAO {
    @Update
    void update(WeightLog weightLogs);

    @Insert
    void insert(WeightLog weightLogs);

    @Delete
    void delete(WeightLog weightLog);

    @Query("select * from weightLogTable")
    List<WeightLog> getAllWeightLogs();

    @Query("select * from weightLogTable where weightLogID = :WeightLogID")
    User getWeightLogByID(float WeightLogID);

    @Query("select * from weightLogTable where weight = :weight")
    User getWeightLogByWeight(float weight);
}
