package com.hello.demo.net.udp;

import java.io.IOException;
import java.net.*;

public class UdpClient {

    private int targetPort;

    private String targetHost;

    private DatagramSocket socket;

    private byte[] buf = new byte[1024];

    public UdpClient(String targetHost, int targetPort) throws SocketException {
        this.targetPort = targetPort;
        this.targetHost = targetHost;
        SocketAddress socketAddress = new InetSocketAddress(targetHost, targetPort);
        socket = new DatagramSocket();
        socket.connect(socketAddress);
        System.out.println("udp client init..");
    }

    public void send(byte[] data, int size) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, size);
        socket.send(packet);
        DatagramPacket response = new DatagramPacket(buf, buf.length);
        socket.receive(response);
        System.out.println("recv: " + new String(response.getData(), 0, response.getLength()) + " length: " + response.getLength() + " from: " + response.getSocketAddress());

    }
}
