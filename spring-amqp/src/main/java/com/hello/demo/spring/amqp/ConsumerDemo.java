package com.hello.demo.spring.amqp;

import com.hello.demo.spring.amqp.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Thread.sleep(10000000L);
    }
}
