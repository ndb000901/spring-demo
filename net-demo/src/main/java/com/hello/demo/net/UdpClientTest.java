package com.hello.demo.net;

import com.hello.demo.net.udp.UdpClient;

import java.io.IOException;

public class UdpClientTest {

    public static void main(String[] args) throws IOException {
        UdpClient client = new UdpClient("192.168.43.242", 20000);
        String message = "hahah123456";
        client.send(message.getBytes(), message.getBytes().length);
    }
}
