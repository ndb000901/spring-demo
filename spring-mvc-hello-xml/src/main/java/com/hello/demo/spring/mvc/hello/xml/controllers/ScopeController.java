package com.hello.demo.spring.mvc.hello.xml.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/scope")
public class ScopeController {

    // 使用HttpServletRequest 向request域对象共享数据
    @GetMapping("/request")
    public String testRequest(HttpServletRequest request) {
        request.setAttribute("hello", "world");
        System.out.println("ScopeController.testRequest run...");
        return "ScopeController.testRequest";
    }

    // 使用ModelAndView 向request域对象共享数据
    @GetMapping("/model-and-view")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView("testModelAndView");
        mv.addObject("hello", "world");
        mv.setViewName("hello");
        System.out.println("ScopeController.testModelAndView run...");
        return mv;
    }

    // 使用Model 向request域对象共享数据
    @GetMapping("/model")
    public String testModel(Model model) {
        model.addAttribute("hello", "world");
        System.out.println("ScopeController.testModel run...");
        return "hello";
    }

    // 使用Map 向request域对象共享数据
    @GetMapping("/map")
    public String testMap(Map<String, Object> map) {
        map.put("hello", "world");
        System.out.println("ScopeController.testMap run...");
        return "hello";
    }

    // 使用ModelMap 向request域对象共享数据
    @GetMapping("/model-map")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("hello", "world");
        System.out.println("ScopeController.testModelMap run...");
        return "hello";
    }

    // 向Session域对象共享数据
    @GetMapping("/session")
    public String testSession(HttpSession session) {
        session.setAttribute("hello", "world");
        System.out.println("ScopeController.testSession run...");
        return "hello";
    }

    // 向application域对象共享数据
    @GetMapping("/application")
    public String testApplication(HttpSession session) {
        ServletContext context = session.getServletContext();
        context.setAttribute("hello", "world");
        return "hello";
    }
}
