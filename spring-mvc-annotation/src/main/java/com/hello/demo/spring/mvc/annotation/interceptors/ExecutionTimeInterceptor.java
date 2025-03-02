package com.hello.demo.spring.mvc.annotation.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ExecutionTimeInterceptor implements HandlerInterceptor {

    private final static Logger logger = LogManager.getLogger(ExecutionTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        logger.info("ExecutionTimeInterceptor.preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long duration = System.currentTimeMillis() - (Long)request.getAttribute("startTime");
        logger.info("ExecutionTimeInterceptor.postHandle, duration: " + duration);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long duration = System.currentTimeMillis() - (Long)request.getAttribute("startTime");
        logger.info("ExecutionTimeInterceptor.afterCompletion, duration: " + duration);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
