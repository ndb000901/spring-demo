package com.hello.demo.nio.udp;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;


public class UdpServer {

    private String host;

    private int port;

    public UdpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() throws IOException {

        Selector selector = Selector.open();
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(host, port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        System.out.println("udp server start... host: " + host + " port: " + port);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        
        while (true) {

            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isReadable()) {

                    DatagramChannel dc = (DatagramChannel) key.channel();
                    buf.clear();
                    SocketAddress remoteAddress = dc.receive(buf);
                    buf.flip();
                    String revcString = new String(buf.array(), 0, buf.limit());
                    System.out.println("recv: " + revcString + " from: " + remoteAddress);

                    // echo
                    dc.send(buf, remoteAddress);

                }
            }

        }


    }
}
