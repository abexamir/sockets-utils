package com.application.messenger;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;


class Server {

    private static ArrayList<Message> messagesDatabase = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        try  {
            DatagramSocket socket = new DatagramSocket(1234);
            byte[] receiveData = new byte[8192];
            byte[] sendData = new byte[8192];
            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                InetAddress clientIpAddress = receivePacket.getAddress();
                System.out.println("Client:- " + clientIpAddress.toString() + " " + sentence);
                int clientPort = receivePacket.getPort();
                if (sentence.startsWith("POST")){
                    int firstSpace = sentence.indexOf(" ");
                    String messageBody = sentence.substring(firstSpace);
                    Message message = new Message(messageBody, clientPort, clientIpAddress);
                    messagesDatabase.add(message);
                    String responseData = "Your message has been sent";
                    sendData = responseData.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
                    socket.send(sendPacket);
                } else if (sentence.startsWith("GET")) {
                    StringBuilder databaseString = new StringBuilder();
                    for (Message message: messagesDatabase) {
                        databaseString.append("\n").append(message.getMessageBody()).append(" ").append(message.getAddress().toString()).append(":").append(message.getPort().toString());
                    }
                    String messageData = databaseString.toString();
                    sendData = messageData.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
                    socket.send(sendPacket);
                    }
                else {
                    String messageData = "Wrong message format";
                    sendData = messageData.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
                    socket.send(sendPacket);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

    }
}