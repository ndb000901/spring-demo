import com.hello.demo.limiter.common.Limiter;
import com.hello.demo.limiter.local.LeakyBucketLimiter;
import com.hello.demo.limiter.redis.RedisLeakyBucketLimiter;
import org.junit.jupiter.api.Test;

public class LeakyBucketLimiterTest {

    @Test
    public void testLocalLeakyBucket() throws InterruptedException {
        Limiter limiter = new LeakyBucketLimiter(3, 1);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }

    }

    @Test
    public void testRedisLeakyBucket() throws InterruptedException {

        Limiter limiter = new RedisLeakyBucketLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "leaky-bucket",
                1,
                3

        );
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }
}
