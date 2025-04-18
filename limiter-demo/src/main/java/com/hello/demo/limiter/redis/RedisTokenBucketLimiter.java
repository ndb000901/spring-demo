package com.hello.demo.limiter.redis;

import com.hello.demo.limiter.common.Limiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.StatefulRedisConnection;

import java.util.Collections;
import java.util.List;

public class RedisTokenBucketLimiter implements Limiter {

    private final RedisCommands<String, String> redis;

    private String hashCode;

    // redis key
    private final String key;

    // 容量
    private final int capacity;

    // 补充速率rate/秒
    private final int rate;

    // 每次请求几个令牌
    private final int tokens;

    private final String luaScript = """
            local key = KEYS[1]
            local capacity = tonumber(ARGV[1])
            local rate = tonumber(ARGV[2])
            local now = tonumber(ARGV[3])
            local requested = tonumber(ARGV[4])
            
            local tokens = tonumber(redis.call("HGET", key, "tokens")) or capacity
            local timestamp = tonumber(redis.call("HGET", key, "timestamp")) or now
            
            local delta = now - timestamp
            local tokenToAdd = (delta / 1000) * rate
            if tokenToAdd > 0 then
                tokens = math.min(capacity, tokens + tokenToAdd)
            end
            
            local allowed = tokens >= requested
            if allowed then
              tokens = tokens - requested
            end
            
            redis.call("HSET", key, "tokens", tokens)
            redis.call("HSET", key, "timestamp", now)
            
            return allowed and 1 or 0
            
            """;

    public RedisTokenBucketLimiter(String redisUrl, String key, int capacity, int rate, int tokens) {
        RedisClient client = RedisClient.create(RedisURI.create(redisUrl));
        StatefulRedisConnection<String, String> connection = client.connect();
        this.redis = connection.sync();
        this.hashCode = redis.scriptLoad(this.luaScript);
        this.key = key;
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = tokens;
    }

    @Override
    public boolean tryAcquire() {

        String[] keys = { key };
        String[] args =
                { String.valueOf(capacity), String.valueOf(rate), String.valueOf(System.currentTimeMillis()), String.valueOf(tokens) };
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
