package com.hello.demo.spring.resource;

import com.hello.demo.spring.resource.config.AppConfig;
import com.hello.demo.spring.resource.service.BeanService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        BeanService beanService = (BeanService) context.getBean(BeanService.class);
        beanService.getBean();
    }
}
