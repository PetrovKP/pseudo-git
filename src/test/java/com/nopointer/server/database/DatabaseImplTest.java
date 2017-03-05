package com.nopointer.server.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseImplTest {
    private static Database database;
    @Test
    public void getInstance() throws Exception {
        database = DatabaseImpl.getInstance();
        assertFalse(database.isConnect());
    }

    @Test
    public void connect() throws Exception {
        database.connect();
        assertTrue(database.isConnect());
    }

    @Test
    public void disconnect() throws Exception {
        database.disconnect();
        assertFalse(database.isConnect());
    }

}