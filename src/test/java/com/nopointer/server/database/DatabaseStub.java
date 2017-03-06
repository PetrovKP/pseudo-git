package com.nopointer.server.database;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DatabaseStub implements Database {
    private DatabaseAPI databaseAPI;

    @Inject
    public DatabaseStub(@Named("STUB") DatabaseAPI databaseAPI) {
        this.databaseAPI = databaseAPI;
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public DatabaseAPI getAPI() {
        return databaseAPI;
    }
}
