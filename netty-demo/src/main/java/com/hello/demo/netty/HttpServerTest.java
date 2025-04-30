package com.hello.demo.netty;

import com.hello.demo.netty.http.HttpServer;

public class HttpServerTest {

    public static void main(String[] args) {
        HttpServer server = new HttpServer("192.168.43.242", 20000);
        server.startServer();
    }
}
