package com.hello.demo.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpServer {

    private String host;

    private int port;

    public UdpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, false)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new UdpServerHandler());
                        }
                    });

            Channel ch = bootstrap.bind(host, port).sync().channel();
            System.out.println("udp server start... host: " + host + " port: " + port);
            ch.closeFuture().await();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            group.shutdownGracefully();
        }
    }
}
