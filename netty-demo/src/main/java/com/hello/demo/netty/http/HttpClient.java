package com.hello.demo.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpClient {

    private String targetHost;

    private int targetPort;

    public HttpClient(String host, int port) {
        this.targetHost = host;
        this.targetPort = port;
    }

    public void send(String message, String path) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new HttpClientCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(new HttpClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect(targetHost, targetPort).sync().channel();
            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1,
                    HttpMethod.POST,
                    path,
                    Unpooled.copiedBuffer(message, CharsetUtil.UTF_8)
            );

            request.headers().set(HttpHeaderNames.HOST, targetHost);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

            channel.writeAndFlush(request);
            channel.closeFuture().sync();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
    }
}
