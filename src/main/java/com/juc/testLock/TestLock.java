package com.juc.testLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 * 一 用于解决多线程安全问题的方式
 * synchronized ：
 * 1 同步代码块
 * 2 同步方法
 * <p>
 * jdk1.5之后
 * 3. 同步锁 lock
 * 注意: 是一个显示锁 通过lock() 方法上锁 必须通过unlock()方法 进行释放锁
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}

class Ticket implements Runnable {

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (tick > 0) {
            lock.lock();
            try {
                Thread.sleep(100);

                System.out.println(Thread.currentThread().getName() + "完成售票: 余票为" + --tick);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
