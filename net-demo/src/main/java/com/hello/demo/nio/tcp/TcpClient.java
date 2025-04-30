package com.hello.demo.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpClient {

    private String host;

    private int port;

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(String message) throws IOException {

        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(host, port));
        channel.configureBlocking(true);

        ByteBuffer buf = ByteBuffer.wrap(message.getBytes());
        channel.write(buf);
        System.out.println("send: " + message + " from: " + channel.getRemoteAddress());

        buf.clear();
        int readSize = channel.read(buf);
        buf.flip();

        String recvString = new String(buf.array(), 0, buf.limit());
        System.out.println("recv: " + recvString + " from: " + channel.getRemoteAddress());
        channel.close();
    }
}
