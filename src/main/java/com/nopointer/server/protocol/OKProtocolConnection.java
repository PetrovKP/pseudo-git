package com.nopointer.server.protocol;

import com.google.inject.Inject;
import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

class OKProtocolConnection implements ProtocolConnection {
    private Socket socket;

    private InputStream sin;
    private OutputStream sout;

    private ObjectInputStream objin;
    private ObjectOutputStream objout;

    private Protocol protocol;

    @Inject
    public OKProtocolConnection(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void setSocket(Socket socket) {
        try {
            this.socket = socket;
            sout = socket.getOutputStream();
            sin = socket.getInputStream();
            objout = new ObjectOutputStream(sout);
            objout.flush();
            objin = new ObjectInputStream(sin) {
                @Override
                protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
                    ObjectStreamClass descriptor = super.readClassDescriptor();
                    if (descriptor.getName().endsWith(".Request")) {
                        return ObjectStreamClass.lookup(Request.class);
                    }
                    return descriptor;
                }
            };

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReady() {
        return socket.isConnected();
    }

    @Override
    public void serveRequest() throws SocketException {
        if (isReady()) {
            try {
                Request request = (Request) objin.readObject();
                System.out.println("Get request: " + request.getType() + " / " + request.getData());
                Response response = protocol.handleRequest(request);
                objout.writeObject(response);
                objout.flush();
            } catch (EOFException e) {
                // readObject() sends exceptions while waiting for new object
            } catch (SocketException e) {
                throw e;
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
