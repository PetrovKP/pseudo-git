package com.nopointer.server.database;

public interface Database {
    void connect();
    void disconnect();
    boolean isConnect();

    DatabaseAPI getAPI();
}
