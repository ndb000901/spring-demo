package com.hello.demo.net;

import com.hello.demo.net.tcp.TcpClient;

import java.io.IOException;

public class TcpClientTest {

    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient("192.168.43.242", 20000);
        String message = "hello\n";
        client.send(message.getBytes(), message.getBytes().length);
    }
}
