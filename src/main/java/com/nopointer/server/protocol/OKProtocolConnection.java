package com.nopointer.server.protocol;

import com.nopointer.server.protocol.entity.Request;
import com.nopointer.server.protocol.entity.Response;

import java.io.*;
import java.net.Socket;

class OKProtocolConnection implements ProtocolConnection {
    private Socket socket;

    private InputStream sin;
    private OutputStream sout;

    private ObjectInputStream objin;
    private ObjectOutputStream objout;

    @Override
    public void setSocket(Socket socket) {
        try {
            this.socket = socket;
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            objin = new ObjectInputStream(sin){
                @Override
                protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
                    ObjectStreamClass descriptor = super.readClassDescriptor();
                    if (descriptor.getName().endsWith(".Request")){
                        return ObjectStreamClass.lookup(Request.class);
                    }
                    return descriptor;
                }
            };

            objout = new ObjectOutputStream(sout);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReady() {
        return socket != null;
    }

    @Override
    public Request getRequest() {
        Request request = null;
        if (socket != null) {
            try {
                request = (Request) objin.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return request;
    }

    @Override
    public void sendResponce(Response response) {
        if (socket != null) {
            try {
                objout.writeObject(response);
                objout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
