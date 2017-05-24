package com.juc.testLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 */

class TestProductorsAndConsumersForLock {

    public static void main(String[] args) {
        Clerks clerk = new Clerks();
        Productors pro = new Productors(clerk);
        Consumers con = new Consumers(clerk);
//        Productors pro1 = new Productors(clerk);
//        Consumers con1 = new Consumers(clerk);
        new Thread(pro, "生产者A").start();
        new Thread(con, "消费者B").start();
        new Thread(pro, "生产者C").start();
        new Thread(con, "消费者D").start();
    }
}

//店员
class Clerks {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int product = 0;

    //进货方法
    public void get() throws InterruptedException {

        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("产品已满!");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //卖货方法
    public void sale() throws InterruptedException {

        lock.lock();
        try {

            while (product <= 0) {
                System.out.println("缺货！");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

//生产者
class Productors implements Runnable {
    private Clerks clerk;

    public Productors(Clerks clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                clerk.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

//消费者
class Consumers implements Runnable {
    private Clerks clerk;

    public Consumers(Clerks clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                clerk.sale();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
