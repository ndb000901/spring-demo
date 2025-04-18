package com.hello.demo.spring.zookeeper;

import com.hello.demo.spring.zookeeper.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ZKDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
    }
}