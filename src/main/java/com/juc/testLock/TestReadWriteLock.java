package com.juc.testLock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 * <p>
 * 1,ReadWriteLock 读写锁
 * 写写 互斥
 * 读写 互斥
 * <p>
 * 读读 不用互斥
 */
public class TestReadWriteLock {
    public static void main(String[] args) {

        ReadWriteLockDemo rw = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set(new Random().nextInt());
            }
        }).start();

        for (int i = 0; i < 100; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    rw.get();
                }
            }).start();

        }
    }

}

class ReadWriteLockDemo {
    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get() {
        lock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + ":" + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    //写
    public void set(int number) {
        lock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "write");
            this.number = number;
        } finally {
            lock.writeLock().unlock();
        }
    }

}
