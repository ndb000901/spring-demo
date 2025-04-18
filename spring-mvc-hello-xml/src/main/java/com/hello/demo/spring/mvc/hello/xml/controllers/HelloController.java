package com.hello.demo.spring.mvc.hello.xml.controllers;

import com.hello.demo.spring.mvc.hello.xml.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/hello"
    )
    public String hello() {
        System.out.println("HelloController.hello run...");
        this.helloService.sayHello();
        return "result: " + System.currentTimeMillis();
    }

    @RequestMapping(value = "/index")
    public String index() {
        System.out.println("HelloController.index run...");
        return "index";
    }
}
