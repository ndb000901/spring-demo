package com.hello.demo.limiter.redis;

import com.hello.demo.limiter.common.Limiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisLeakyBucketLimiter implements Limiter {

    private final RedisCommands<String, String> redis;

    private String hashCode;

    private final String bucketKey;

    private final int leakRate;

    private final int capacity;

    private final String luaScript = """
            -- KEYS[1]: 漏桶的 Redis key
            -- ARGV[1]: 当前时间戳（ms）
            -- ARGV[2]: 漏水速率（单位：每秒请求数）
            -- ARGV[3]: 桶容量
            
            local bucketKey = KEYS[1]
            local now = tonumber(ARGV[1])
            local leakRate = tonumber(ARGV[2])
            local capacity = tonumber(ARGV[3])
            
            -- 获取当前桶状态
            local lastTime = tonumber(redis.call("HGET", bucketKey, "last_time")) or now
            local water = tonumber(redis.call("HGET", bucketKey, "water")) or 0
            
            -- 计算漏水量
            local deltaTime = math.max(0, now - lastTime) / 1000
            local leaked = deltaTime * leakRate
            local newWater = math.max(0, water - leaked)
            
            -- 是否能接收新请求
            if newWater + 1 <= capacity then
                newWater = newWater + 1
                redis.call("HSET", bucketKey, "water", newWater)
                redis.call("HSET", bucketKey, "last_time", now)
                -- redis.call("EXPIRE", bucketKey, 3600)  -- 1小时过期清理
                return 1
            else
                return 0
            end
            
            """;

    public RedisLeakyBucketLimiter(String redisUrl, String bucketKey, int leakRate, int capacity) {
        RedisClient client = RedisClient.create(RedisURI.create(redisUrl));
        StatefulRedisConnection<String, String> connection = client.connect();
        this.redis = connection.sync();
        this.bucketKey = bucketKey;
        this.leakRate = leakRate;
        this.capacity = capacity;

        // 加载 Lua 脚本
        this.hashCode = redis.scriptLoad(this.luaScript);
    }

    @Override
    public boolean tryAcquire() {

        long now = System.currentTimeMillis();
        String[] keys = { this.bucketKey };
        String[] args = { String.valueOf(now), String.valueOf(leakRate), String.valueOf((capacity))};
        try {

            // 尝试用缓存 SHA 调用
            Long res = redis.evalsha(hashCode, ScriptOutputType.INTEGER, keys, args);
            return res == 1;
        } catch (Exception e) {
            // 脚本丢失，回退 eval 并重新缓存
            Long res = redis.eval(this.luaScript, ScriptOutputType.INTEGER, keys, args);
            // 重新缓存
            this.hashCode = redis.scriptLoad(this.luaScript);
            return res == 1;
        }
    }
}
