package com.hello.demo.spring.mvc.annotation.config;

import com.hello.demo.spring.mvc.annotation.interceptors.AuthInterceptor;
import com.hello.demo.spring.mvc.annotation.interceptors.ExecutionTimeInterceptor;
import com.hello.demo.spring.mvc.annotation.interceptors.LogInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
//@ComponentScan("com.hello")
public class WebAppConfig implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new AuthInterceptor());
        registry.addInterceptor(new ExecutionTimeInterceptor());
    }
}
