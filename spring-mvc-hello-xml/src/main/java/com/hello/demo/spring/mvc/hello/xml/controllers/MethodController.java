package com.hello.demo.spring.mvc.hello.xml.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/method")
public class MethodController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getMethod() {
        System.out.println("MethodController.getMethod run...");
        return "MethodController.getMethod";
    }

    @PostMapping(value = "/post")
    public String postMethod() {
        System.out.println("MethodController.postMethod run...");
        return "MethodController.postMethod";
    }

    @PutMapping(value = "/put")
    public String putMethod() {
        System.out.println("MethodController.putMethod run...");
        return "MethodController.putMethod";
    }

    @DeleteMapping("/delete")
    public String deleteMethod() {
        System.out.println("MethodController.deleteMethod run...");
        return "MethodController.deleteMethod";
    }

    @PatchMapping(value = "/patch")
    public String patchMethod() {
        System.out.println("MethodController.patchMethod run...");
        return "MethodController.patchMethod";
    }

    @RequestMapping(value = "/params", method = RequestMethod.GET, params = {"user=admin", "password!=123456", "token"})
    public String testParams(String user, String password, String token) {
        System.out.println("MethodController.testParams run...");
        System.out.println("user: " + user + ", password: " + password + ", token: " + token);
        return "MethodController.testParams: " + "user: " + user + ", password: " + password + ", token: " + token;
    }

    @RequestMapping(value = "/headers", method = RequestMethod.GET, headers = {"user=admin", "password!=123456", "token"})
    public String testHeaders(
            @RequestHeader("user") String user,
            @RequestHeader("password") String password,
            @RequestHeader("token") String token
    ) {
        System.out.println("MethodController.testHeaders run...");
        System.out.println("user: " + user + ", password: " + password + ", token: " + token);
        return "MethodController.testHeaders  " + "user: " + user + ", password: " + password + ", token: " + token;
    }

    @RequestMapping(value = "/path/{id}", method = RequestMethod.GET)
    public String testPath(@PathVariable("id") String id) {
        System.out.println("MethodController.testPath run...");
        return "MethodController.testPath: " + id;
    }

}
