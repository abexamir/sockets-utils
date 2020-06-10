package com.serati.hw3.part1;

import java.io.PrintWriter;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345, 4);
            int clientsCount = 0;
            serverSocket.setSoTimeout(10000);
            while (clientsCount <= 4){
                System.out.println("Waiting for clients");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected");
                ClientThread clientThread = new ClientThread(clientSocket, clientsCount);
                clientThread.start();
                clientsCount ++;
            }
            Socket notAcceptedClient = serverSocket.accept();
            PrintWriter out1 = new PrintWriter(notAcceptedClient.getOutputStream(),true);
            out1.println("The server is full");
            notAcceptedClient.close();
        }
        catch (Exception e){
            e.toString();
        }
    }
}
