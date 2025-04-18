package com.hello.demo.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("MyCallable run...");
        return "MyCallable";
    }
}
