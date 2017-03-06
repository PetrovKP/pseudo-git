package com.nopointer.server.database;

public interface Database {
    void connect();

    void disconnect();

    DatabaseAPI getAPI();
}
