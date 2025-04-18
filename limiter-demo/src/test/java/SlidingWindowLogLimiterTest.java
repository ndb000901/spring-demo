import com.hello.demo.limiter.common.Limiter;
import com.hello.demo.limiter.local.SlidingWindowLogLimiter;
import com.hello.demo.limiter.redis.RedisSlidingWindowLogLimiter;
import org.junit.jupiter.api.Test;

public class SlidingWindowLogLimiterTest {


    @Test
    public void testLocalSlidingWindowLogLimiter() throws InterruptedException {
        Limiter limiter = new SlidingWindowLogLimiter(3, 1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }

    @Test
    public void testRedisSlidingWindowCounterLimiter() throws InterruptedException {
        Limiter limiter = new RedisSlidingWindowLogLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "sliding-window-log",
                3,
                1000
        );

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }


}
