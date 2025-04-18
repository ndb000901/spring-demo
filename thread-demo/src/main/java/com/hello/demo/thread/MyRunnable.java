package com.hello.demo.thread;

public class MyRunnable implements Runnable {
    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("MyRunnable run...: " + id);
    }
}
