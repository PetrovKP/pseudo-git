package com.nopointer.server.database;

import com.google.inject.Inject;

public class DatabaseStub implements Database {
    private DatabaseAPI databaseAPI;

    @Inject
    public DatabaseStub(DatabaseAPI databaseAPI) {
        this.databaseAPI = databaseAPI;
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnect() {
        return false;
    }

    @Override
    public DatabaseAPI getAPI() {
        return databaseAPI;
    }
}
