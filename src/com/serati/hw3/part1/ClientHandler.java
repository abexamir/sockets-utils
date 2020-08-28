package com.serati.hw3.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private Socket client = null;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream());
    }
    @Override
    public void run() {
        try {
            while (true) {
                out.println("Hello, Please input you first number");
                String number1 = in.readLine();
                out.println("Please input you second number");
                String number2 = in.readLine();
                int result = Integer.parseInt(number1) + Integer.parseInt(number2);
                out.println("The sum of these two numbers equals to: " + result);
            }
        }

        catch (Exception e) {
            System.err.println(e.getStackTrace());
        }

        finally {
            out.close();
            try {
                in.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
