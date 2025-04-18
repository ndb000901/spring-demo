package com.hello.demo.limiter.local;

import com.hello.demo.limiter.common.Limiter;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowLogLimiter implements Limiter {

    // 最大请求数
    private final int maxRequests;

    // 时间窗口（毫秒）
    private final long windowSizeInMillis;

    // 存储请求时间戳
    private final Deque<Long> timestamps = new LinkedList<>();

    // 构造函数
    public SlidingWindowLogLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();

        // 移除窗口外的旧请求
        while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= windowSizeInMillis) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < maxRequests) {
            timestamps.addLast(now);
            return true;
        } else {
            return false;
        }
    }
}
