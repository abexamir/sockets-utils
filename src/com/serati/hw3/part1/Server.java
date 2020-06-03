package com.serati.hw3.part1;

import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            int clientsCount = 0;
            while (clientsCount <= 4){
                System.out.println("Waiting for clients");
                Socket clientSocket = serverSocket.accept();
                clientsCount ++;
                System.out.println("Client is connected");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        }
        catch (Exception e){
            e.toString();
        }
    }
}
