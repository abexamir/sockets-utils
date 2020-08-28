package com.serati.hw3.part2;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];  //store incoming data
        boolean stop = false;
        while(!stop) {
            String stringSendData = scanner.nextLine();
            sendData = stringSendData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9090);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            receiveData = receivePacket.getData();
            String stringReceiveData = new String(receiveData);
            System.out.println("FROM SERVER: " + stringReceiveData);
            clientSocket.close();
        }
        clientSocket.close();
    }
}