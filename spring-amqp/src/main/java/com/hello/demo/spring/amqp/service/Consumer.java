package com.hello.demo.spring.amqp.service;

import com.hello.demo.spring.amqp.common.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = "hello_event")
    public void handle(Message message) {
        System.out.println("message: " + message.toString());
    }
}
