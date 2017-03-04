package com.nopointer.server.client;

import java.net.Socket;

public abstract class ClientConnection extends Thread{
    protected Socket connection;

    public ClientConnection(Socket connection) {
        this.connection = connection;
    }
}
