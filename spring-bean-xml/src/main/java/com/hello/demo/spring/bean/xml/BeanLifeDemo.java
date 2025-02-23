package com.hello.demo.spring.bean.xml;

import com.hello.demo.spring.bean.xml.common.User3;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean-life.xml");
        User3 user1 = (User3) context.getBean("user1");

        System.out.println(user1);
        context.close();

    }
}
