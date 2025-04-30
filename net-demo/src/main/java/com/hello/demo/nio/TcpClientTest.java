package com.hello.demo.nio;

import com.hello.demo.nio.tcp.TcpClient;

import java.io.IOException;

public class TcpClientTest {

    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient("192.168.43.242", 20000);
        client.send("hahah00000");
    }
}
