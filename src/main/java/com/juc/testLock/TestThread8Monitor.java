package com.juc.testLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 * 1 两个普通同步方法 两个线程标准打印  打印的是one 还是 two 打印 // one two
 * 2 新增Sleep方法 打印 one two
 * 3 新增getThree  打印 // three one two
 *
 *
 * 线程八锁的关键点在于
 *      1非静态方法的锁默认为this 静态方法的锁 对应的class实例
 *      2 在某一个时刻内 只能有一个线程持有锁 无论几个方法
 *
 */
public class TestThread8Monitor {

    public static void main(String[] args) {
        Number number = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();


    }
}


class  Number{
    public synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("one");

    }

    public synchronized void getTwo(){
        System.out.println("two");
    }
    public void getThree(){
        System.out.println("three");
    }

}