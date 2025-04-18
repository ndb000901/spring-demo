package com.hello.demo.spring.validator.config;

import com.hello.demo.spring.validator.common.User1;
import com.hello.demo.spring.validator.common.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan("com.hello")
public class AppConfig {

    @Bean("user1")
    public User1 getUser1() {
        User1 user1 = new User1();
        user1.setAge(1000);
        user1.setName("user1");
        user1.setEmail("user1gmail.com");
        user1.setPhoneNumber("12345678");
        return user1;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public static MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean("user2")
    public User2 getUser2() {
        User2 user2 = new User2();
        user2.setName("helloA");
        return user2;
    }
}
