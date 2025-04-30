package com.hello.demo.netty;

import com.hello.demo.netty.http.HttpClient;

public class HttpClientTest {
    public static void main(String[] args) {
        HttpClient client = new HttpClient("192.168.43.242", 20000);
        client.send("hahah123456789", "/api/hello");
    }
}
