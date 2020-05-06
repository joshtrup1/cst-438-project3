package com.example.cst_438_project3.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cst_438_project3.DB.AppDatabase;

@Entity(tableName = AppDatabase.WEIGHT_LOG_TABLE)
public class WeightLog {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int WeightLogID;


    @NonNull
    private float weight;



    public WeightLog() {}

    @Ignore
    public WeightLog(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getWeightLogID() {
        return WeightLogID;
    }

    public void setWeightLogID(int weightLogID) {
        this.WeightLogID = weightLogID;
    }

    @Override
    public String toString() {
        return
                "Entry # : " + WeightLogID + " || Weight:" + weight + '\n';
    }
}
