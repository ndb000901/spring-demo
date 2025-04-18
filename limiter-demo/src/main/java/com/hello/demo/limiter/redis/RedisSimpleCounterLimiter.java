package com.hello.demo.limiter.redis;

import com.hello.demo.limiter.common.Limiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


public class RedisSimpleCounterLimiter implements Limiter {

    private final RedisCommands<String, String> redis;

    private final String luaScript = """
            local current = redis.call("INCR", KEYS[1])
            if current == 1 then
                redis.call("EXPIRE", KEYS[1], tonumber(ARGV[2]))
            end
            
            if current <= tonumber(ARGV[1]) then
                return 1
            else
                return 0
            end
            """;


    private String hashCode;

    // 每个窗口最大请求数
    private final int maxRequests;

    // 窗口大小，秒
    private final int windowSeconds;

    // redis key
    private final String key;

    public RedisSimpleCounterLimiter(String redisUrl, String key, int maxRequests, int windowSeconds) {

        RedisClient client = RedisClient.create(RedisURI.create(redisUrl));
        StatefulRedisConnection<String, String> connection = client.connect();
        this.redis = connection.sync();
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
        this.key = key;

        // 预加载脚本
        this.hashCode = redis.scriptLoad(luaScript);
    }

    @Override
    public boolean tryAcquire() {
        String[] keys = { this.key };
        String[] args = { String.valueOf(this.maxRequests), String.valueOf(this.windowSeconds) };

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
