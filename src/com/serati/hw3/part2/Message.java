package com.serati.hw3.part2;

import java.net.InetAddress;

public class Message {
    private String messageBody;
    private Integer port;
    private InetAddress address;

    public Message(String messageBody, int port, InetAddress address){
        this.messageBody = messageBody;
        this. port = port;
        this.address = address;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Integer getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }
}
