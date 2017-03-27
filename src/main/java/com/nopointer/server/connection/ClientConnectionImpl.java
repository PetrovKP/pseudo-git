package com.nopointer.server.connection;

import com.google.inject.Inject;
import com.nopointer.server.protocol.ProtocolConnection;

import java.net.Socket;
import java.net.SocketException;

public class ClientConnectionImpl extends ClientConnection {

    private ProtocolConnection protocolConnection;

    @Inject
    public ClientConnectionImpl(ProtocolConnection protocolConnection) {
        this.protocolConnection = protocolConnection;
    }

    @Override
    public void setSocket(Socket socket) {
        super.setSocket(socket);
        protocolConnection.setSocket(socket);
    }

    @Override
    public void run() {
        while (true) {
            try {
                protocolConnection.serveRequest();
            } catch (SocketException e) {
                break;
            }
        }
    }
}
