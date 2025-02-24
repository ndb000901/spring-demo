package com.hello.demo.spring.aop.xml.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class LoggingAspect {


    // execution(访问修饰符  返回类型  包名.类名.方法名(参数列表) 异常类型)

    public void before(JoinPoint joinPoint) {
        System.out.println("before run...  -->" + joinPoint.getSignature().getName());
    }

    public void after(JoinPoint joinPoint) {
        System.out.println("after run...  --->" + joinPoint.getSignature().getName());
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturning run...  --->" + joinPoint.getSignature().getName() + " result: " + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("afterThrowing run..." + ex.getMessage());
    }


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
