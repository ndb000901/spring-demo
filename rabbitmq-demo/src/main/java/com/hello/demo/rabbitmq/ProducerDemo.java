package com.hello.demo.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.demo.rabbitmq.common.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerDemo {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.242");
        factory.setPort(5672);
        factory.setUsername("hello");
        factory.setPassword("hello123456");

        Connection connection = factory.newConnection();
        // 虚拟信道，复用同一tcp连接
        Channel channel = connection.createChannel();
        // 创建交换机
        channel.exchangeDeclare("logs", "topic", true);
        // 创建队列
        channel.queueDeclare("logs_info", true,false, false, null);
        // 绑定(交换机与队列建立联系)
        channel.queueBind("logs_info", "logs", "log.info");

        Message message = new Message();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(message);

        // 发布消息
        channel.basicPublish("logs", "log.info", null, json.getBytes());

        connection.close();
    }
}