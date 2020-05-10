
package com.example.cst_438_project3.Objects;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout")
public class Workout {

    @PrimaryKey
    private int userID;
    private String tow; //type of workout
    private String reps;
    private String startTime;
    private String endTime;
    private  String date;

    public Workout(String tow, String reps, String startTime, String endTime, String date, int userID){
        this.tow = tow;
        this.reps = reps;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public String getTow() {
        return tow;
    }


    public void setTow(String tow) {
        this.tow = tow;
    }

    public String getReps(){
        return reps;
    }

    public void setReps(String reps){
        this.reps = reps;
    }

    public java.lang.String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public java.lang.String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public void setDate(String date) {this.date = date;}
    public String getDate() {return date;}
}

// package com.example.cst_438_project3.Objects;

// import androidx.annotation.NonNull;
// import androidx.room.Entity;
// import androidx.room.Ignore;
// import androidx.room.PrimaryKey;

// import com.example.cst_438_project3.DB.AppDatabase;

// @Entity(tableName = AppDatabase.WORKOUT_TABLE)
// public class Workout {

//     @PrimaryKey(autoGenerate = true)
//     @NonNull
//     private int WorkoutID;


//     @NonNull
//     private String name;
//     @NonNull
//     private String description;
//     @NonNull
//     private int reps;


//     public Workout() {}

//     @Ignore
//     public Workout(String name, String description, int reps ) {
//         this.name = name;
//         this.description = description;
//         this.reps = reps;
//     }

//     public int getWorkoutID() {
//         return WorkoutID;
//     }

//     public void setWorkoutID(int workoutID) {
//         WorkoutID = workoutID;
//     }

//     @NonNull
//     public String getName() {
//         return name;
//     }

//     public void setName(@NonNull String name) {
//         this.name = name;
//     }

//     @NonNull
//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(@NonNull String description) {
//         this.description = description;
//     }

//     public int getReps() {
//         return reps;
//     }

//     public void setReps(int reps) {
//         this.reps = reps;
//     }

//     @Override
//     public String toString() {
//         return "Workout{" +
//                 "name='" + name + '\'' +
//                 ", description='" + description + '\'' +
//                 ", reps=" + reps +
//                 '}';
//     }
// }

