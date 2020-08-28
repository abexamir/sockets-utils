package com.serati.hw3.part2;
import java.net.*;
import java.util.ArrayList;


class Server {
    public static void main(String args[]) throws Exception
    {
        try
        {
            DatagramSocket socket = new DatagramSocket(1234);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                ArrayList<Message> messagesDatabase = new ArrayList<>();
                InetAddress clientIpAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                if (sentence.startsWith("POST")){
                    int firstSpace = sentence.indexOf("");
                    String messageBody = sentence.substring(firstSpace);
                    Message message = new Message(messageBody, clientPort, clientIpAddress);
                    messagesDatabase.add(message);
                } else if (sentence.equals("GET")) {
                    for (Message message: messagesDatabase){
                        String messageData = "Message" + message.getMessageBody() + "From address" + message.getAddress().toString() + "and Port" + message.getPort().toString();
                        sendData = messageData.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
                        socket.send(sendPacket);
                    }
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