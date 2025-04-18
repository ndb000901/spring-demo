package com.hello.demo.limiter.redis;

import com.hello.demo.limiter.common.Limiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.UUID;

public class RedisSlidingWindowCounterLimiter implements Limiter {

    private final RedisCommands<String, String> redis;

    // redis key
    private final String key;

    // 窗口大小
    private final long windowSizeMillis;

    // 窗口最大请求数
    private final int maxRequests;

    private String hashCode;

    private final String luaScript = """
            -- KEYS[1]: zset key
            -- ARGV[1]: 当前时间（毫秒）
            -- ARGV[2]: 窗口大小（毫秒）
            -- ARGV[3]: 最大请求数
            -- ARGV[4]: 唯一ID
            
            local key = KEYS[1]
            local now = tonumber(ARGV[1])
            local window = tonumber(ARGV[2])
            local maxReq = tonumber(ARGV[3])
            local member = ARGV[4]
            local minScore = now - window
            
            -- 移除窗口外的记录
            redis.call('ZREMRANGEBYSCORE', key, 0, minScore)
            
            -- 获取窗口内数量
            local count = redis.call('ZCARD', key)
            
            if count < maxReq then
              -- 添加当前请求（用时间戳做唯一标识）
              redis.call('ZADD', key, now, member)
              -- 设置过期时间，防止内存泄露
              redis.call('PEXPIRE', key, window)
              return 1
            else
              return 0
            end
            """;

    public RedisSlidingWindowCounterLimiter(String redisUri, String key, int maxRequests, long windowSizeMillis) {
        RedisClient client = RedisClient.create(redisUri);
        this.redis = client.connect().sync();
        this.key = key;
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.hashCode = redis.scriptLoad(luaScript);
    }

    @Override
    public boolean tryAcquire() {

        String[] keys = { key };
        String[] args = {
                String.valueOf(System.currentTimeMillis()),
                String.valueOf(windowSizeMillis),
                String.valueOf(maxRequests),
                UUID.randomUUID().toString()
        };

        try {
            // 尝试用缓存 SHA 调用
            Long res = redis.evalsha(hashCode, ScriptOutputType.INTEGER, keys, args);
            return res == 1;
        } catch (Exception e) {
            // 脚本丢失，回退 eval 并重新缓存
            Long res = redis.eval(luaScript, ScriptOutputType.INTEGER, keys, args);
            // 重新缓存
            hashCode = redis.scriptLoad(luaScript);
            return res == 1;
        }
    }
}
