package com.hello.demo.nio;

import com.hello.demo.nio.udp.UdpClient;

import java.io.IOException;

public class UdpClientTest {

    public static void main(String[] args) throws IOException {
        UdpClient client = new UdpClient("192.168.43.242", 20000);
        client.send("haha09876");
    }
}
