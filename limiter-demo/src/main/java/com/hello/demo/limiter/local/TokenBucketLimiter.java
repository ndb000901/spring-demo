package com.hello.demo.limiter.local;
import com.hello.demo.limiter.common.Limiter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 本地内存令牌桶限流器
 */
public class TokenBucketLimiter implements Limiter {

    // 每秒生成的令牌数
    private final int rate;
    // 桶的容量
    private final int capacity;

    // 当前令牌数
    private double tokens;
    // 上一次生成令牌的时间（毫秒）
    private long lastRefillTimestamp;

    // 锁，保证线程安全
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketLimiter(int rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    /**
     * 尝试获取一个令牌
     * @return true = 成功获取，false = 被限流
     */
    @Override
    public boolean tryAcquire() {
        lock.lock();
        try {
            System.out.println("tryAcquire->tokens: " + tokens);
            refill();

            if (tokens >= 1) {
                tokens -= 1;
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据时间补充令牌
     */
    private void refill() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTimestamp;

        double tokensToAdd = (elapsed / 1000.0) * rate;
//        System.out.println("elapsed: " + elapsed + " tokensToAdd: " + tokensToAdd);
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
