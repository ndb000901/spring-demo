package com.hello.demo.limiter.local;

import com.hello.demo.limiter.common.Limiter;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounterLimiter implements Limiter {

    // 每个窗口最大请求数
    private final int maxRequests;

    // 窗口大小，单位毫秒
    private final long windowSizeInMillis;

    private final Counter counter;


    public SimpleCounterLimiter(int maxRequestsPerWindow, long windowSizeInMillis) {
        this.maxRequests = maxRequestsPerWindow;
        this.windowSizeInMillis = windowSizeInMillis;
        this.counter = new Counter(this.windowSizeInMillis, new AtomicInteger(0));
    }

    @Override
    public boolean tryAcquire() {
        long currentWindow = System.currentTimeMillis() / windowSizeInMillis;

        synchronized (counter) {
            if (counter.window != currentWindow) {
                counter.window = currentWindow;
                counter.count.set(0);
            }
            return counter.count.incrementAndGet() <= maxRequests;
        }
    }

    // 内部类：计数器
    private static class Counter {
        long window;
        AtomicInteger count;

        Counter(long window, AtomicInteger count) {
            this.window = window;
            this.count = count;
        }
    }
}
