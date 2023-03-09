package org.example;

public class Main {
    public static void main(String[] args) {
        final var server = new TetrisServer(8080);

        server.start();

    }
}