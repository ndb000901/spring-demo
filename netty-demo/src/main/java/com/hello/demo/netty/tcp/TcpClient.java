package com.hello.demo.netty.tcp;

import com.sun.source.doctree.SeeTree;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TcpClient {

    private String targetHost;

    private int targetPort;

    public TcpClient(String targetHost, int port) {
        this.targetHost = targetHost;
        this.targetPort = port;

    }

    public void send(String message) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new TcpClientHandler());

                        }
                    });
            ChannelFuture f = bootstrap.connect(targetHost, targetPort).sync();
            ByteBuf buf = Unpooled.copiedBuffer(message, CharsetUtil.UTF_8);
            f.channel().writeAndFlush(message).await();
            f.channel().closeFuture().sync();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }

    }
}
