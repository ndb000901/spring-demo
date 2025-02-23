package com.hello.demo.spring.bean.xml;

import com.hello.demo.spring.bean.xml.common.User3;
import com.hello.demo.spring.bean.xml.common.UserFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean-factory.xml");
        User3 user1 = (User3)context.getBean("userFactoryBean");
        UserFactoryBean userFactoryBean = (UserFactoryBean)context.getBean("&userFactoryBean");
        System.out.println(user1.toString());
        System.out.println(userFactoryBean.toString());
    }
}
