package com.hello.demo.net.udp;

import java.io.IOException;
import java.net.*;

public class UdpServer {

    private String host;

    private int port;

    private DatagramSocket socket;

    private final byte[] buf = new byte[1024];

    public UdpServer(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void startServer() throws IOException {
        SocketAddress address = new InetSocketAddress(host, port);
        this.socket = new DatagramSocket(address);
        System.out.println("UDP server start... host: " + host + " port: " + port);

        while (true) {
            DatagramPacket requst = new DatagramPacket(buf, buf.length);
            socket.receive(requst);
            String message = new String(requst.getData(), 0, requst.getLength());
            System.out.println("UDP server receive message: " + message + " addr: " + requst.getAddress() + " port: " + requst.getPort());
            DatagramPacket response = new DatagramPacket(
                    requst.getData(),
                    requst.getLength(),
                    requst.getAddress(),
                    requst.getPort()
            );
            socket.send(response);
        }
    }
}
