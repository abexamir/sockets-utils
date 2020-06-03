package com.serati.hw3.part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket = null;

    public ClientThread(Socket socket){
        this.socket = socket;

    }
    @Override
    public void run() {
        try {
            PrintWriter out1 = new PrintWriter(socket.getOutputStream(),true);
            out1.println("Hello, Please input you first number");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String number1 = input.readLine();
            out1.println("Please input you second number");
            String number2 = input.readLine();
            int result = Integer.parseInt(number1) + Integer.parseInt(number2);
            out1.println("The sum of these two numbers equals to: " + result);
        }

        catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
