package com.hello.demo.netty;

import com.hello.demo.netty.udp.UdpClient;

public class UdpClientTest {

    public static void main(String[] args) {
        UdpClient client = new UdpClient("192.168.43.242", 20000);
        client.send("hello123456");
    }
}
