package com.application.clienthandler;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 12345;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);
    public static void main(String[] args) {
        try {
//            serverSocket.setSoTimeout(10000);
            while (true) {
                ServerSocket serverSocket = new ServerSocket(PORT);
                serverSocket.setSoTimeout(10000);
                System.out.println("Waiting for clients");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected");
                ClientHandler clientThread = new ClientHandler(clientSocket);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
//            Socket notAcceptedClient = serverSocket.accept();
//            PrintWriter out1 = new PrintWriter(notAcceptedClient.getOutputStream(),true);
//            out1.println("The server is full");
//            notAcceptedClient.close();
        }
        catch (Exception e){
            e.toString();
        }
    }
}
