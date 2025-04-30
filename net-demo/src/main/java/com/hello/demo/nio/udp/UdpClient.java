package com.hello.demo.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UdpClient {

    private String host;

    private int port;

    public UdpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(String message) throws IOException {

        SocketAddress address = new InetSocketAddress(host, port);

        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(true);


        ByteBuffer sendBuf = ByteBuffer.wrap(message.getBytes());

        ByteBuffer recvBuf = ByteBuffer.allocate(1024);
        recvBuf.clear();

        channel.send(sendBuf, address);

        SocketAddress remoteAddress = channel.receive(recvBuf);

        if (remoteAddress != null) {
            recvBuf.flip();
            String recvString = new String(recvBuf.array(), 0, recvBuf.limit());
            System.out.println("recv: " + recvString + " from: " + remoteAddress);
        }

        channel.close();

    }
}
