package com.hello.demo.spring.amqp;

import com.hello.demo.spring.amqp.config.AppConfig;
import com.hello.demo.spring.amqp.service.Producer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProducerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Producer producer = context.getBean(Producer.class);
        for (int i = 0; i < 100000000; i++) {
            producer.send();
        }


        context.close();
    }
}