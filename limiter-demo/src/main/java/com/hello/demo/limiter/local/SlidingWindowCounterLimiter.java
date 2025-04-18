package com.hello.demo.limiter.local;

import com.hello.demo.limiter.common.Limiter;

import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounterLimiter implements Limiter {

    // 每个子窗口的宽度（单位：毫秒）
    private final long bucketSizeMillis;

    // 子窗口数量（整个滑动窗口总长 = bucketSizeMillis * bucketCount）
    private final int bucketCount;

    // 每个子窗口的计数器
    private final AtomicInteger[] counters;

    // 每个子窗口的时间戳（标识当前格子的起始时间）
    private final long[] timestamps;

    // 总请求数限制
    private final int maxRequests;

    public SlidingWindowCounterLimiter(long bucketSizeMillis, int bucketCount, int maxRequests) {
        this.bucketSizeMillis = bucketSizeMillis;
        this.bucketCount = bucketCount;
        this.maxRequests = maxRequests;

        this.counters = new AtomicInteger[bucketCount];
        this.timestamps = new long[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            counters[i] = new AtomicInteger(0);
            timestamps[i] = 0;
        }
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long windowStart = now - bucketSizeMillis * bucketCount;
        int totalCount = 0;

        for (int i = 0; i < bucketCount; i++) {
            if (timestamps[i] <= windowStart) {
                // 旧窗口，重置
                counters[i].set(0);
                timestamps[i] = now - (now % bucketSizeMillis) + i * bucketSizeMillis;
            }
            totalCount += counters[i].get();
        }

        if (totalCount >= maxRequests) {
            return false;
        }

        int currentIndex = (int) ((now / bucketSizeMillis) % bucketCount);
        counters[currentIndex].incrementAndGet();
        // 对齐时间
        timestamps[currentIndex] = now - (now % bucketSizeMillis);

        return true;
    }
}
