package com.example.cst_438_project3.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkoutTest {

    @Test
    public void Workout() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        assertNotNull(w);
    }

    @Test
    public void setUserID() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setUserID(1);
        assertTrue(w.getUserID() == 1);
    }

    @Test
    public void setWorkoutID() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setWorkoutID(1);
        assertTrue(w.getWorkoutID() == 1);
    }

    @Test
    public void setTow() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setTow("sit-ups");
        assertTrue(w.getTow() == "sit-ups");
    }

    @Test
    public void setReps() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setReps("40");
        assertTrue(w.getReps() == "40");
    }

    @Test
    public void setStartTime() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setStartTime("1:30");
        assertTrue(w.getStartTime() == "1:30");
    }

    @Test
    public void setEndTime() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setEndTime("2:00");
        assertTrue(w.getEndTime() == "2:00");
    }

    @Test
    public void setDate() {
        Workout w = new Workout("push-ups", "20", "1:00", "1:30", "5/9/20", 1);
        w.setDate("5/5/20");
        assertTrue(w.getDate() == "5/5/20");
    }
}