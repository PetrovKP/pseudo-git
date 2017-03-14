package com.nopointer.server.protocol;

import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.sql.SQLException;

public interface Protocol {
    Response handleRequest(Request request) throws SQLException;
}
