import com.hello.demo.limiter.common.Limiter;
import com.hello.demo.limiter.local.SimpleCounterLimiter;
import com.hello.demo.limiter.redis.RedisSimpleCounterLimiter;
import com.hello.demo.limiter.redis.RedisTokenBucketLimiter;
import org.junit.jupiter.api.Test;

public class SimpleCounterLimiterTest {

    @Test
    public void testLocalSimpleCounter() throws InterruptedException {
        Limiter limiter = new SimpleCounterLimiter(1, 1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }

    @Test
    public void testRedisSimpleCounter() throws InterruptedException {

        Limiter limiter = new RedisSimpleCounterLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "simple-counter",
                3,
                10
        );
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }
}
