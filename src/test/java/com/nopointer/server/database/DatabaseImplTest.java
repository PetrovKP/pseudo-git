package com.nopointer.server.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseImplTest {
    private static Database database = DatabaseImpl.getInstance();
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