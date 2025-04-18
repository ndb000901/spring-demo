package com.hello.demo.limiter.redis;

import com.hello.demo.limiter.common.Limiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.UUID;

public class RedisSlidingWindowLogLimiter implements Limiter  {

    private final RedisCommands<String, String> redis;

    private final String key;

    private final int maxRequests;

    private final long windowSizeInMillis;

    private String hashCode;

    private final String luaScript = """
            -- KEYS[1]    = 限流key
            -- ARGV[1]    = 当前时间戳（毫秒）
            -- ARGV[2]    = 时间窗口（毫秒）
            -- ARGV[3]    = 最大请求数
            -- ARGV[4]    = score/member唯一值（UUID）
            
            local now = tonumber(ARGV[1])
            local window = tonumber(ARGV[2])
            local max_requests = tonumber(ARGV[3])
            local member = ARGV[4]
            local key = KEYS[1]
            
            -- 移除窗口外数据
            redis.call("ZREMRANGEBYSCORE", key, 0, now - window)
            
            -- 当前窗口请求数
            local count = redis.call("ZCARD", key)
            
            if count < max_requests then
                redis.call("ZADD", key, now, member)
                redis.call("EXPIRE", key, math.ceil(window / 1000) + 1)
                return 1
            else
                return 0
            end
            
            """;



    public RedisSlidingWindowLogLimiter(String redisUri, String key, int maxRequests, long windowSizeInMillis) {
        this.redis = RedisClient.create(redisUri).connect().sync();
        this.key = key;
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.hashCode = redis.scriptLoad(luaScript);
    }



    @Override
    public boolean tryAcquire() {

        String[] keys = { key };
        String[] args = {
                String.valueOf(System.currentTimeMillis()),
                String.valueOf(windowSizeInMillis),
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
