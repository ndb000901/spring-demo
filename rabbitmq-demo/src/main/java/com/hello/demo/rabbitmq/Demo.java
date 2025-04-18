package com.hello.demo.rabbitmq;

import com.hello.demo.rabbitmq.common.Message;


public class Demo {

    static class TestDemo {
        static Message quickSort = new Message();
        Message quickSort1 = new Message();

        void f1() throws InterruptedException {
            Message quickSort = new Message();
            System.out.println("done");
            Thread.sleep(1000_000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestDemo testDemo = new TestDemo();
        testDemo.f1();
    }
}


