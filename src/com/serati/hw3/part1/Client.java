package com.serati.hw3.part1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            System.out.println("server ip address: " + serverAddress.getHostAddress());
            Socket socket = new Socket(serverAddress, 12345);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(inFromServer.readLine());
            outToServer.writeBytes((inFromUser.readLine()) + '\n'); //first number
            System.out.println(inFromServer.readLine());
            outToServer.writeBytes((inFromUser.readLine()) + '\n'); //second number
            System.out.println(inFromServer.readLine());
            socket.close();

        }
        catch (UnknownHostException e1){
            System.out.println("Unknown host exception" + e1.toString());
        }
        catch (IOException e2){
            System.out.println("IOException" + e2.toString());
        }
        catch (IllegalArgumentException e3){
            System.out.println("Illegal Argument Exception" + e3.toString());
        }
        catch (Exception e){
            System.out.println("Other exceptionss" + e.toString());
        }
    }
}
