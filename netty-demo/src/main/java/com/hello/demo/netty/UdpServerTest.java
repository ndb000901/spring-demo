package com.hello.demo.netty;

import com.hello.demo.netty.udp.UdpServer;

public class UdpServerTest {

    public static void main(String[] args) {
        UdpServer server = new UdpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
