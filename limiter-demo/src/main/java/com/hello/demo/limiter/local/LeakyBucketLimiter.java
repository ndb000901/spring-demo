package com.hello.demo.limiter.local;

import com.hello.demo.limiter.common.Limiter;

public class LeakyBucketLimiter implements Limiter {

    // 漏桶容量（最多允许多少个请求在桶中等待处理）
    private final int capacity;

    // 漏水速率（每秒允许处理多少个请求）
    private final double leakRatePerSecond;

    // 当前水量（请求数量）
    private double water;

    // 上次漏水时间（毫秒）
    private long lastLeakTime;

    public LeakyBucketLimiter(int capacity, double leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 距离上次漏水的时间（秒）
        double elapsedSeconds = (now - lastLeakTime) / 1_000.0;

        // 漏掉的水量 = 时间 * 速率
        double leakedWater = elapsedSeconds * leakRatePerSecond;

        // 更新当前桶中水量（不能为负）
        water = Math.max(0, water - leakedWater);

        // 更新时间
        lastLeakTime = now;

        // 判断当前水位是否还有空间放新请求
        if (water + 1 <= capacity) {
            water += 1;
            // 放进来了，请求允许
            return true;
        } else {
            // 桶满了，请求被拒绝
            return false;
        }
    }
}
