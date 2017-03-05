package com.nopointer.server.protocol;

import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

public interface Protocol {
    Response handleRequest(Request request);
}
