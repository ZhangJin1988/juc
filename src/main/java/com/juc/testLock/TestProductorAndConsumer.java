package com.juc.testLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 *
 * 为了避免虚假唤醒问题 应该总是应用在循环当中
 */
public class TestProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor pro = new Productor(clerk);
        Consumer con = new Consumer(clerk);
//        Productor pro1 = new Productor(clerk);
//        Consumer con1 = new Consumer(clerk);
        new Thread(pro, "生产者A").start();
        new Thread(con, "消费者B").start();
        new Thread(pro, "生产者C").start();
        new Thread(con, "消费者D").start();
    }
}

//店员
class Clerk {
    private int product = 0;

    //进货方法
    public synchronized void get() throws InterruptedException {

        while (product >= 1) {
            System.out.println("产品已满!");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();
    }

    //卖货方法
    public synchronized void sale() throws InterruptedException {
        while (product <= 0) {
            System.out.println("缺货！");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();

    }
}

//生产者
class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
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
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
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