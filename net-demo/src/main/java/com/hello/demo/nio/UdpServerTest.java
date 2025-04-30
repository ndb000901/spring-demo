package com.hello.demo.nio;

import com.hello.demo.nio.udp.UdpServer;

import java.io.IOException;

public class UdpServerTest {

    public static void main(String[] args) throws IOException {

        UdpServer server = new UdpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
