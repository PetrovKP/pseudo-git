package com.nopointer.server.database;

import java.sql.Connection;

public interface Database {
    void connect();
    void disconnect();
    boolean isConnect();

    DatabaseAPI getAPI();
}
