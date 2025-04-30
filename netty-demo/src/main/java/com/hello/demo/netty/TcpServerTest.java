package com.hello.demo.netty;

import com.hello.demo.netty.tcp.TcpServer;

public class TcpServerTest {

    public static void main(String[] args) {
        TcpServer server = new TcpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
