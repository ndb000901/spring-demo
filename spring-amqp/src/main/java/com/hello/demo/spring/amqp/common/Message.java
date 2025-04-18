package com.hello.demo.spring.amqp.common;


import lombok.Data;

@Data
public class Message {

    private String messageId;

    private String createAt;

    private String createBy;

    private String message;

    private String taskId;

    private String taskName;

    private String taskType;
}
