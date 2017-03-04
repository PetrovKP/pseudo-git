package com.nopointer.server;

import com.nopointer.server.client.ClientConnection;
import com.nopointer.server.client.ClientConnectionImpl;
import com.nopointer.server.database.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private int port;
    private List<ClientConnection> connections;

    Database database;

    public Server(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(this.port);
            this.connections = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        System.out.println("Starting server...");
        if (serverSocket!=null){
            System.out.println("Server started!");
            while(true){
                try {
                    Socket client = serverSocket.accept();
                    ClientConnection clientConnection = new ClientConnectionImpl(client);
                    connections.add(clientConnection);
                    clientConnection.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
