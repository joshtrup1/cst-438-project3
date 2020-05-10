package com.example.cst_438_project3.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightLogTest {
    @Test
    public void WeightLog() {
        WeightLog w = new WeightLog();
        assertNotNull(w);
    }

    @Test
    public void setWeight() {
        WeightLog w = new WeightLog();
        w.setWeight(150);
        assertTrue(w.getWeight() == 150);
    }

    @Test
    public void setWeightLogID() {
        WeightLog w = new WeightLog();
        w.setWeightLogID(1);
        assertTrue(w.getWeightLogID() == 1);
    }

    @Test
    public void setUserID() {
        WeightLog w = new WeightLog();
        w.setUserID(1);
        assertTrue(w.getUserID() == 1);
    }
}