package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                // You can implement database operations here and send back responses to the
                // client
                out.println("Server received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
