package com.hello.demo.spring.mvc.hello.xml.controllers;

import com.hello.demo.spring.mvc.hello.xml.exception.CustomBadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping("/div")
    public String testDiv(int a, int b) {
        return "a / b = " + (a / b);
    }

    @GetMapping("/default")
    public String testDefault() throws IOException {
        throw new IOException("io error");
    }

    @GetMapping("/custom-bad")
    public void testCustomBad() {
        throw new CustomBadRequestException("自定义badRequest");
    }
}
