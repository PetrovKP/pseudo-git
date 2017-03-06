package com.nopointer.server.database;

import java.sql.Connection;

public interface Database {
    Connection connect();
    void disconnect();
    boolean isConnect();

    DatabaseAPI getAPI();
}
