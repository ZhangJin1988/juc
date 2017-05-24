package com.juc.testPool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhangjin
 * @since 2017/5/24
 */
public class TestScheduledThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {

            ScheduledFuture<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    return num;

                }
            }, 1, TimeUnit.SECONDS);
            System.out.println(result.get());
        }

        pool.shutdown();

    }
}
