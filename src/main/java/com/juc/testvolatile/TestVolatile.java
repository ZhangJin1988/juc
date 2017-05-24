package com.juc.testvolatile;

/**
 * @author zhangjin
 * @since 2017/5/24
 * <p>
 * 内存可见性问题  当多个线程操作共享数据时 彼此不可见的问题
 *
 * 1 valatile 关键字  多个线程进行操作共享数据时  可以保证内存中的数据是可见的
 *
 * 就相当于 直接操作主存中的数据
 *
 * 相较与 synchronized 是一个轻量级的锁
 *
 * 注意：
 *  1 valatile不具备 互斥性
 *  2 valatile不能保证 变量的原子性
 *
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            synchronized (td) {

                if (td.isFlag()) {
                    System.out.println("-------------");
                    break;
                }
            }

        }
    }
}

class ThreadDemo implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        flag = true;

        System.out.println("flag = " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
