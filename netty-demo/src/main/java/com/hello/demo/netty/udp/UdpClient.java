package com.hello.demo.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class UdpClient {

    private String targetHost;

    private int targetPort;

    public UdpClient(String targetHost, int targetPort) {
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    public void send(String message) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new UdpClientHandler());
                        }
                    });
            Channel ch = bootstrap.connect(targetHost, targetPort).sync().channel();

            DatagramPacket request = new DatagramPacket(
                    Unpooled.copiedBuffer(message, StandardCharsets.UTF_8),
                    new InetSocketAddress(targetHost, targetPort)
            );
            ch.writeAndFlush(request).sync();

            if (!ch.closeFuture().await(5000)) {
                System.out.println("client timeout");
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
    }
}
