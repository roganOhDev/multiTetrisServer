package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TetrisServer {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients;

    public TetrisServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            System.out.println("Server Running in Port : " + serverSocket.getLocalPort());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        while (true) {
            try {
                final var socket = serverSocket.accept();
                System.out.println("New Client Connected : " + socket.getRemoteSocketAddress());
                final var client = new ClientHandler(socket, clients);

                clients.add(client);

                client.start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
