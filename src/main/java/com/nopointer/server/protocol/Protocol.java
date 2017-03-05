package com.nopointer.server.protocol;

public interface Protocol {
    Responce handleRequest(Request request);
}
