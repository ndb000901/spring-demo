package com.hello.demo.spring.mvc.hello.xml.controllers;

import com.hello.demo.spring.mvc.hello.xml.common.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/params")
public class ParamsController {

    // 通过 HttpServletRequest 获取参数
    @RequestMapping(value = "/request", params = {"name", "password"})
    public String testRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("ParamsController.testRequest " + "name: " + name + " password" + password);
        return "ParamsController.testRequest " + "name:" + name + " password" + password;
    }

    // 通过控制器方法
    @RequestMapping(value = "/method-params", params = {"name", "password"})
    public String testMethodParams(String name, String password) {
        System.out.println("ParamsController.testMethodParams " + "name: " + name + " password" + password);
        return "ParamsController.testMethodParams " + "name:" + name + " password" + password;
    }

    // 通过@RequestParam获取参数
    @RequestMapping(value = "/request-param", params = { "password" })
    public String testRequestParam(
            @RequestParam(value = "name", required = false, defaultValue = "admin") String name,
            @RequestParam(value = "password", required = true) String password
    ) {
        System.out.println("ParamsController.testRequestParam " + "name: " + name + " password" + password);
        return "ParamsController.testRequestParam " + "name:" + name + " password" + password;
    }

    // 通过对象获取参数
    @RequestMapping(value = "/pojo", params = { "name", "password" })
    public String testPojo(User user) {
        System.out.println("ParamsController.testPojo " + user);
        return "ParamsController.testPojo " + user;
    }

    // 获取请求头
    @RequestMapping(value = "/headers", headers = {"password"})
    public String testHeaders(
            @RequestHeader(value = "name", required = false, defaultValue = "admin") String name,
            @RequestHeader(value = "password", required = true) String password
    ) {
        System.out.println("ParamsController.testHeaders " + "name: " + name + " password" + password);
        return "ParamsController.testHeaders " + "name:" + name + " password" + password;
    }

    // 获取cookies
    @RequestMapping(value = "/cookies")
    public String testCookies(
            @CookieValue(value = "name", required = true, defaultValue = "admin") String name,
            @CookieValue(value = "password", required = true) String password
    ) {
        System.out.println("ParamsController.testCookies " + "name: " + name + " password" + password);
        return "ParamsController.testCookies " + "name:" + name + " password: " + password;
    }

    @RequestMapping(value = "/post", params = { "name", "password" }, method = RequestMethod.POST)
    public String testPost(User user) {
        System.out.println("ParamsController.testPost " + user);
        return "ParamsController.testPost " + user;
    }




}
