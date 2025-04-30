package com.hello.demo.net.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpClient {

    private String targetHost;

    private int targetPort;

    private Socket socket;

    private byte[] buf = new byte[1024];

    public TcpClient(String targetHost, int targetPort) throws IOException {
        this.targetHost = targetHost;
        this.targetPort = targetPort;
        SocketAddress address = new InetSocketAddress(targetHost, targetPort);
        socket = new Socket();
        socket.connect(address);
    }

    public void send(byte[] data, int size) {

        try (

                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        ) {
            out.write(data, 0, size);
            out.flush();
            System.out.println("send: " + new String(data, 0, size));
            int length = in.read(buf);
            System.out.println("recv: " + new String(buf, 0, length));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.out.println(("client close"));
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
