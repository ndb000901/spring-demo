package com.hello.demo.spring.aop.xml.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.hello.demo.spring.aop.annotation.service.UserService.*(..))")
    public void getPointcut() {

    }
    // execution(访问修饰符  返回类型  包名.类名.方法名(参数列表) 异常类型)

    @Order(5) // 数字小的优先级高
    @Before("execution(public * com.hello.demo.spring.aop.annotation.service.UserService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("before run...  -->" + joinPoint.getSignature().getName());
    }

    @Order(10)
    @Before("execution(public * com.hello.demo.spring.aop.annotation.service.UserService.*(..))")
    public void before2(JoinPoint joinPoint) {
        System.out.println("before2 run...  -->" + joinPoint.getSignature().getName());
    }

    @After("execution(* com.hello.demo.spring.aop.annotation.service.UserService.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("after run...  --->" + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            value = "execution(* com.hello.demo.spring.aop.annotation.service.UserService.*(..))",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturning run...  --->" + joinPoint.getSignature().getName() + " result: " + result);
    }

    @AfterThrowing(
            value = "execution(* com.hello.demo.spring.aop.annotation.service.UserService.*(..))",
            throwing = "ex"
    )
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("afterThrowing run..." + ex.getMessage());
    }

    // 从Pointcut方法获取
    @Around("getPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("around-->目标对象方法执行之前");
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("around-->目标对象方法返回值之后");
        } catch (Throwable e) {
            System.out.println("around-->目标对象方法出现异常时 -->" + e.getMessage());
        } finally {
            System.out.println("around-->目标对象方法执行完毕");
        }
        return result;
    }
}
