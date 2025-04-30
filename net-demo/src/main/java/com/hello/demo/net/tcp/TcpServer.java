package com.hello.demo.net.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpServer {

    private String host;

    private int port;

    private ServerSocket socket;


    public TcpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() {
        SocketAddress address = new InetSocketAddress(host, port);
        try {
            socket = new ServerSocket();
            socket.bind(address, 32);

            System.out.println("tcp server start... host: " + host + " port: " + port);

            while (true) {
                Socket clientSocket = socket.accept();

                new Thread(() -> clientHandle(clientSocket)).start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public static void clientHandle(Socket clientSocket) {


        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream())
                );
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("tcp server receive message: " + message);
                out.write(message);
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                System.out.println("关闭客户端连接: " + clientSocket.getRemoteSocketAddress());
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("关闭 socket 出错: " + e.getMessage());
            }
        }
    }
}
