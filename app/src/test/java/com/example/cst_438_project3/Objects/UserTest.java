package com.example.cst_438_project3.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void User() {
        User u = new User();
        assertNotNull(u);
    }

    @Test
    public void setUsername() {
        User u = new User();
        u.setUsername("Test1");
        assertTrue(u.getUsername() == "Test1");
    }

    @Test
    public void setPassword() {
        User u = new User();
        u.setPassword("Test2");
        assertTrue(u.getPassword() == "Test2");
    }

    @Test
    public void setUserID() {
        User u = new User();
        u.setPassword("1");
        assertTrue(u.getPassword() == "1");
    }
}