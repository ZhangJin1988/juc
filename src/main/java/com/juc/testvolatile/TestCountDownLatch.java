package com.juc.testvolatile;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangjin
 * @since 2017/5/24
 * CountDownLatch 闭锁
 * 在完成某些运算时，只有当其他所有的线程的运算全部完成 当前运算才继续执行
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();

        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为:" + (end - start));

    }
}

class LatchDemo implements Runnable {
    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        synchronized (this) {
            try {

                for (int i = 0; i < 5000; i++) {

                    if (i % 2 == 0) {
                        System.out.println(i);
                    }

                }
            } catch (Exception e) {

            } finally {
                latch.countDown();
            }

        }

    }
}
