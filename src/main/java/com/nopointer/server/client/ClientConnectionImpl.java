package com.nopointer.server.client;

import java.net.Socket;

public class ClientConnectionImpl extends ClientConnection {

    public ClientConnectionImpl(Socket connection) {
        super(connection);
    }

    @Override
    public void run() {
        super.run();
        while (true){

        }
    }
}
