package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private ArrayList<ClientHandler> clients;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(final Socket socket, final ArrayList<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                //tetris game
                sendToAllClients("Here's A New Challenger!".getBytes());
            }

        } catch (IOException e) {
            System.out.println("Error Handling While Client Connection : " + e.getMessage());

        } finally {
            clients.remove(this);
            System.out.println("Client Disconnected: " + socket.getRemoteSocketAddress());
        }
    }

    private void sendToAllClients(final byte[] data) throws IOException {
        clients.forEach(client -> {
            try {
                sendToOtherClients(client, data);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        });
    }

    private void sendToOtherClients(final ClientHandler client, final byte[] data) throws IOException {
        if (client != this) {
            client.out.write(data);
            client.out.flush();
        }
    }
}
