package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            System.out.println("Server Running in Port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                final var socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
