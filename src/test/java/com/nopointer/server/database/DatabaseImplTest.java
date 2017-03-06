package com.nopointer.server.database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseImplTest {
    private static DatabaseImpl database;

    @Before
    public void setUp() throws Exception {
        database = DatabaseImpl.getInstance();
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

}