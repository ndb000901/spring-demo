package com.hello.demo.net;

import com.hello.demo.net.tcp.TcpServer;

public class TcpServerTest {

    public static void main(String[] args) {
        TcpServer server = new TcpServer("192.168.43.242", 20000);

        server.startServer();
    }
}
