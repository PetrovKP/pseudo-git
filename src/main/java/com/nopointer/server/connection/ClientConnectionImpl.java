package com.nopointer.server.connection;

import com.google.inject.Inject;
import com.nopointer.server.protocol.Protocol;
import com.nopointer.server.protocol.ProtocolConnection;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.net.Socket;

class ClientConnectionImpl extends ClientConnection {

    private ProtocolConnection protocolConnection;
    private Protocol protocol;

    @Inject
    public ClientConnectionImpl(ProtocolConnection protocolConnection,
                                Protocol protocol) {
        this.protocolConnection = protocolConnection;
        this.protocol = protocol;
    }

    @Override
    public void setSocket(Socket socket) {
        super.setSocket(socket);
        protocolConnection.setSocket(socket);
    }

    @Override
    public void run() {
        while (true) {
            Request request = protocolConnection.getRequest();
            Response response = protocol.handleRequest(request);
            protocolConnection.sendResponce(response);
        }
    }
}
