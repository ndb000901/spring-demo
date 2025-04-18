package com.hello.demo.spring.junit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

// 方法1
@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean.xml")
@ContextConfiguration(classes = UserService.class)

// 方法2
// @SpringJUnitConfig(locations = "classpath:bean.xml")
public class SpringXmlTest {

    @Autowired
    private UserService userService;


    @Test
    public void test() {
        this.userService.getUser();
    }

}