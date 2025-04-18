package com.hello.demo.spring.i18n;

import com.hello.demo.spring.i18n.config.AppConfig;
import com.hello.demo.spring.i18n.service.BeanService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class I18nDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        BeanService beanService = (BeanService) context.getBean("beanService");
        beanService.getMessage();
    }
}