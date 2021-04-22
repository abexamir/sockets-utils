package com.application.clienthandler;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 12345);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String serverResponse = input.readLine();
            System.out.println(serverResponse);
            System.out.println(">");
            input.close();
            out.close();
            socket.close();
        }


    }
}
