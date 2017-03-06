package com.nopointer.server.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.Module;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseAPIGitTest {
    private DatabaseAPI databaseAPI;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new Module());
        Database database = injector.getInstance(Database.class);

        Connection connection = database.connect();
        databaseAPI = injector.getInstance(DatabaseAPI.class);
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