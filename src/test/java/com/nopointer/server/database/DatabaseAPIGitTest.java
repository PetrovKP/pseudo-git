package com.nopointer.server.database;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseAPIGitTest {
    private DatabaseAPI databaseAPI;
    private Database database;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        database = DatabaseImpl.getInstance();
        connection = database.connect();

        databaseAPI = new DatabaseAPIGit(connection);
    }

    @Test
    public void registerUser() throws Exception {
        boolean logout;
        databaseAPI.registerUser("ADD", "123");
        logout = databaseAPI.login("ADD", "123");
        assertTrue(logout);

        databaseAPI.registerUser("ADD", "145");
        logout = databaseAPI.login("ADD", "123");
        assertTrue(logout);
    }

    @Test
    public void login() throws Exception {
        boolean logout;
        logout = databaseAPI.login("petrov", "qwerty123");
        assertTrue(logout);

        logout = databaseAPI.login("ovcharuk", "12332");
        assertFalse(logout);
    }

}