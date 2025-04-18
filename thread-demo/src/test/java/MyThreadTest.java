import com.hello.demo.thread.MyCallable;
import com.hello.demo.thread.MyRunnable;
import com.hello.demo.thread.MyThread;
import com.hello.demo.thread.SumTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadTest {

    @Test
    public void testMyThread() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(myThread.getState());
        myThread.join();
        System.out.println("testMyThread run...");
    }

    @Test
    public void testMyRunnable() throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable(1);
        Thread thread = new Thread(myRunnable, "myRunnable");
        thread.start();
        thread.join();
        System.out.println("testMyRunnable run...");
    }

    @Test
    public void testMyCallable() {

        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask, "myCallable");
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyRunnable(i));
        }

    }

    @Test
    public void testThreadPool() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(new MyRunnable(i));
        }


    }

    @Test
    public void testAtomic() throws InterruptedException {
        AtomicInteger num = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    num.incrementAndGet();
                }
            });
            threads.add(thread);
            thread.start();

        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(num);

    }

    private int num = 0;

    @Test
    public void testAdd() throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    num++;
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(num);
    }

    @Test
    public void testCondition() throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                lock.lock();
                System.out.println("t1->lock: " + i);
                num.incrementAndGet();
                if (num.get() == 10) {
                    condition.signal();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println("t2 lock");
            try {
                System.out.println("< 10 t2 wait");
                condition.await();
                System.out.println("t2-->: " + num.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("main run...");
    }

    @Test
    public void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {

                    Thread.sleep(1000);
                    System.out.println("run");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main run...");
    }

    @Test
    public void testCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println(Thread.currentThread().getName() + "冲啊");
        });
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "到达");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "继续");
            }, "t" + i);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("main");

    }

    @Test
    public void testSemaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " acquire");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(Thread.currentThread().getName() + " release");
                    semaphore.release();
                }

            }, "t" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("main run...");
    }

    @Test
    public void testExchanger() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread t1 = new Thread(() -> {
            String data = "t1->data";
            System.out.println("t1 send: " + data);

            try {
                String recv = exchanger.exchange(data);
                System.out.println("t1 recv: " + recv);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Thread t2 = new Thread(() -> {
            String data = "t2->data";
            System.out.println("t2 send: " + data);

            try {
                String recv = exchanger.exchange(data);
                System.out.println("t2 recv: " + recv);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void testLockSupport() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("thread run...");
            LockSupport.park();
            System.out.println("thread end...");
        });
        thread.start();

        Thread.sleep(1000);

        LockSupport.unpark(thread);
        System.out.println("main run...");
    }

    @Test
    public void testScheduledThreadPoolExecutor() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPoolExecutor.schedule(() -> {
                System.out.println("run ...");
                countDownLatch.countDown();
            }, 1000, TimeUnit.MILLISECONDS);
        }
        scheduledThreadPoolExecutor.shutdown();
        countDownLatch.await();
        System.out.println("main run ....");
    }

    @Test
    public void testFutureTask() {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("run...");
            return "hello";
        });
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThreadLocal() {
//        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
//        threadLocal.set(0);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                Integer data = threadLocal.get();
                threadLocal.set(data + 1);
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            }, "t" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("main run..");
    }

    @Test
    public void testSumTask() {
        SumTask sumTask = new SumTask(1L, 1000000);
        ForkJoinPool pool = new ForkJoinPool(); // 默认CPU核数线程
        long result = pool.invoke(sumTask);
        System.out.println("结果: " + result);
    }
}
