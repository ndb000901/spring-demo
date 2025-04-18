import com.hello.demo.limiter.common.Limiter;
import com.hello.demo.limiter.local.SlidingWindowCounterLimiter;
import com.hello.demo.limiter.redis.RedisSlidingWindowCounterLimiter;
import org.junit.jupiter.api.Test;

public class SlidingWindowCounterLimiterTest {

    @Test
    public void testLocalSlidingWindowCounterLimiter() throws InterruptedException {
        Limiter limiter = new SlidingWindowCounterLimiter(100, 10, 3);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }

    @Test
    public void testRedisSlidingWindowCounterLimiter() throws InterruptedException {

        Limiter limiter = new RedisSlidingWindowCounterLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "sliding-window",
                6000,
                1000
        );
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }

        int ok = 0;
        int num = 1000000;
        for (int i = 0; i < num; i++) {
            if (limiter.tryAcquire()) {
                ok++;
            }
            if (i % 100000 == 0) {
                System.out.println("round: " + i);
            }
        }
        System.out.println("ok--> " + ok);
        System.out.println("error--> " + (num - ok));
    }


}
