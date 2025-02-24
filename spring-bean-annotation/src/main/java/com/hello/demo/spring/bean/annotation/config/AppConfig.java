package com.hello.demo.spring.bean.annotation.config;

import com.hello.demo.spring.bean.annotation.controllers.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserController getUserController() {
        return new UserController();
    }
}
