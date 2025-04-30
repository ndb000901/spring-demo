package com.hello.demo.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class TcpServer {

    private String host;

    private int port;

    public TcpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() throws IOException {

        Selector selector = Selector.open();


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(host, port));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("tcp server start... host: " + host + " port: " + port);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("new connect: " + clientChannel.getRemoteAddress());
                }
                else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    buf.clear();
                    int readSize = clientChannel.read(buf);
                    if (readSize == -1) {
                        System.out.println("cloas connection: " + clientChannel.getRemoteAddress());
                        clientChannel.close();
                        key.cancel();;
                        continue;
                    }
                    buf.flip();
                    String recvString = new String(buf.array(), 0, buf.limit());
                    System.out.println("recv: " + recvString + " from: " + clientChannel.getRemoteAddress());
                    clientChannel.write(buf);
                }
            }

        }

    }
}
