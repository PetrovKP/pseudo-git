package com.nopointer.server;

import com.google.inject.Injector;
import com.nopointer.server.connection.ClientConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private int port = 12345;

    private ServerSocket serverSocket;
    private List<ClientConnection> connections;
    private Injector injector;

    private Server(Injector injector) {
        try {
            this.injector = injector;
            this.serverSocket = new ServerSocket(this.port);
            this.connections = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server create(Injector injector) {
        return new Server(injector);
    }

    public void start() {
        System.out.println("Starting server...");
        if (serverSocket != null) {
            System.out.println("Server started!");
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    ClientConnection clientConnection = injector.getInstance(ClientConnection.class);
                    clientConnection.setSocket(client);
                    connections.add(clientConnection);
                    clientConnection.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
