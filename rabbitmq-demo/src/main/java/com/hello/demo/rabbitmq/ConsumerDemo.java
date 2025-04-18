package com.hello.demo.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.demo.rabbitmq.common.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ConsumerDemo {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.242");
        factory.setPort(5672);
        factory.setUsername("hello");
        factory.setPassword("hello123456");

        Connection connection = factory.newConnection();
        // 虚拟信道，复用同一tcp连接
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            System.out.println("consumerTag: " + consumerTag);
            String data = new String(delivery.getBody(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Message message = objectMapper.readValue(data, Message.class);
            System.out.println("message: " + message.toString());
        };
        channel.basicConsume("logs_info", true, deliverCallback, consumerTag -> {});
        channel.close();
    }
}
