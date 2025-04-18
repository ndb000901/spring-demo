package com.hello.demo.spring.amqp.service;

import com.hello.demo.spring.amqp.common.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Random random = new Random();

    public void send() {
        String taskId = UUID.randomUUID().toString();
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setCreateAt(String.valueOf(System.currentTimeMillis()));
        message.setCreateBy(String.valueOf(random.nextInt(1, Integer.MAX_VALUE)));
        message.setTaskId(taskId);
        message.setTaskType("notify");
        message.setTaskName("name-" + taskId);
        message.setMessage("message-" + taskId);
        try {
            rabbitTemplate.convertAndSend("hello_exchange", "default", message);
        }
        catch (Exception e) {
            System.out.println(message);

        }

    }
}
