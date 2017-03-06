package com.nopointer.server.connection;

import java.net.Socket;

public abstract class ClientConnection extends Thread {
    protected Socket connection;

    public void setSocket(Socket socket) {
        this.connection = socket;
    }

}
