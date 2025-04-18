package com.hello.demo.rabbitmq.common;

import java.util.UUID;

public class Message {

    private String messageId;

    private String message;

    private String createAt;

    public Message() {
        this.messageId = UUID.randomUUID().toString();
        this.message = "Hello: " + UUID.randomUUID().toString();
        this.createAt = String.valueOf(System.currentTimeMillis());
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
