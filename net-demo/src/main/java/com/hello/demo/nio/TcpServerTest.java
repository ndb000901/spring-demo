package com.hello.demo.nio;

import com.hello.demo.nio.tcp.TcpServer;

import java.io.IOException;

public class TcpServerTest {

    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
