package com.hello.demo.net;

import com.hello.demo.net.udp.UdpServer;

import java.io.IOException;

public class UdpServerTest {

    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
