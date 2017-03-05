package com.nopointer.server.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseImplTest {
    @Test
    public void connect() throws Exception {
        Database database = DatabaseImpl.getInstance();
        database.connect();
        assertTrue(database.isConnect());
    }

    @Test
    public void disconnect() throws Exception {
        Database database = DatabaseImpl.getInstance();
        database.connect();
        assertTrue(database.isConnect());

        database.disconnect();
        assertFalse(database.isConnect());
    }

}