package com.hello.demo.spring.mvc.hello.xml.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/interceptor")
public class InterceptorController {

    private final static Logger logger = LogManager.getLogger(InterceptorController.class);

    @GetMapping("/test")
    public String testInterceptor() {
        logger.info("InterceptorController.testInterceptor run...");
        return "InterceptorController.testInterceptor";
    }
}
