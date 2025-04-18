package com.hello.demo.spring.mvc.annotation.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger logger = LogManager.getLogger(AuthInterceptor.class);

    private final String tokenType = "Bearer";

    private final String token = "admin123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        String tokenStr = request.getHeader("Authorization");
        String[] tokenArray = tokenStr.split(" ");
        if (tokenArray.length == 2 && tokenArray[0].equals(tokenType) && tokenArray[1].equals(token)) {
            logger.info("AuthInterceptor.preHandle true");
            return true;
        }
        logger.info("AuthInterceptor.preHandle false");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        logger.info("AuthInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        logger.info("AuthInterceptor.afterCompletion");
    }
}
