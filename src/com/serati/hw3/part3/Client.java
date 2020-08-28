package com.serati.hw3.part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

    BufferedReader br1, br2;
    PrintWriter pr1;
    Socket socket;
    Thread t1, t2;
    String in = "", out = "";

    public Client() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            socket = new Socket("localhost", 5000);
            t1.start();
            t2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            if (Thread.currentThread() == t2) {
                do {
                    br1 = new BufferedReader(new InputStreamReader(System.in));
                    pr1 = new PrintWriter(socket.getOutputStream(), true);
                    in = br1.readLine();
                    pr1.println(in);
                } while (!in.equals("GOOD BYE"));
            } else {
                do {
                    br2 = new BufferedReader(new   InputStreamReader(socket.getInputStream()));
                    out = br2.readLine();
                    System.out.println("[+] Server: " + out);
                } while (!out.equals("GOOD BYE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}