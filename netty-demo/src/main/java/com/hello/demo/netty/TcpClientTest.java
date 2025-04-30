package com.hello.demo.netty;

import com.hello.demo.netty.tcp.TcpClient;

public class TcpClientTest {
    public static void main(String[] args) {
        TcpClient client = new TcpClient("192.168.43.242", 20000);
        client.send("12345678");
    }
}
