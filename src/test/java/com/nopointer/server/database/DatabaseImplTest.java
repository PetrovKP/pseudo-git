package com.nopointer.server.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nopointer.server.config.Module;
import com.nopointer.server.config.TestModule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseImplTest {
    private Database database;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestDatabaseModule());
        database = injector.getInstance(Database.class);
    }

    @Test
    public void connect() throws Exception {
        database.connect();
        assertTrue(database.isConnect());
    }

    @Test
    public void disconnect() throws Exception {
        database.connect();
        database.disconnect();
        assertFalse(database.isConnect());

        database.disconnect();
        assertFalse(database.isConnect());
    }

    @Test
    public void getAPI() throws Exception {
        database.connect();
        database.getAPI().login("ADD", "123");

        database.disconnect();
    }


    @Test(expected = SQLException.class)
    public void canTryConnectExpected() throws SQLException{
        Injector injector = Guice.createInjector(new TestDatabaseError());
        database = injector.getInstance(Database.class);
        database.connect();
    }

}
