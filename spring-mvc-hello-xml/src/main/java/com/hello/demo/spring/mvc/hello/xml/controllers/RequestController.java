package com.hello.demo.spring.mvc.hello.xml.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/request")
@ResponseBody
public class RequestController {

    @RequestMapping("/request-body")
    public String testRequestBody(@RequestBody String body) {

        System.out.println("body: " + body);
        return "RequestController.testRequestBody: " + body;
    }

    @RequestMapping("/request-entity")
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        System.out.println("body: " + requestEntity.getBody());
        System.out.println("headers: " + requestEntity.getHeaders());
        return "RequestController.testRequestEntity";
    }

    @PostMapping("/response-entity")
    public ResponseEntity<String> testResponseEntity(RequestEntity<String> entity) {

        System.out.println("RequestController.testResponseEntity");
        return ResponseEntity.ok("Rec: " + entity);
    }


}
