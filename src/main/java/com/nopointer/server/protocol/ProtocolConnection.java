package com.nopointer.server.protocol;

import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.net.Socket;

public interface ProtocolConnection {
    void setSocket(Socket socket);
    boolean isReady();
    Request getRequest();
    void sendResponce(Response response);
}
