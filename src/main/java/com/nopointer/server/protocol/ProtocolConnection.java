package com.nopointer.server.protocol;

import java.net.Socket;
import java.net.SocketException;

public interface ProtocolConnection {
    void setSocket(Socket socket);

    boolean isReady();

    void serveRequest() throws SocketException;

}
