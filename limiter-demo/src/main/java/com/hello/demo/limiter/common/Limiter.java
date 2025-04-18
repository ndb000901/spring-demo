package com.hello.demo.limiter.common;

public interface Limiter {

    boolean tryAcquire();
}
