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

public class ConsumerConfirm {

    // 策略1: 自动确认
    public static void case1() throws IOException, TimeoutException {
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

    // 策略2: 手动确认
    public static void case2() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.242");
        factory.setPort(5672);
        factory.setUsername("hello");
        factory.setPassword("hello123456");

        Connection connection = factory.newConnection();
        // 虚拟信道，复用同一tcp连接
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                System.out.println("consumerTag: " + consumerTag);
                String data = new String(delivery.getBody(), StandardCharsets.UTF_8);
                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(data, Message.class);
                System.out.println("message: " + message.toString());
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            } catch (Exception e) {
                // 未确认
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
                // 支持批量未确认
                // channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            }

        };
        channel.basicConsume("logs_info", false, deliverCallback, consumerTag -> {});

//        channel.close();
    }

    public static void main(String[] args) {
        try {
            case2();
        }
        catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
}
