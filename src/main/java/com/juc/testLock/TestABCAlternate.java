package com.juc.testLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjin
 * @since 2017/5/24
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要 求输出的结果必须按顺序显示。
 * 如:ABCABCABC...... 依次递归
 */
public class TestABCAlternate {
    public static void main(String[] args) {

        Alternate alternate = new Alternate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    alternate.loopA(i);
                    System.out.println("---------------");

                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    alternate.loopB(i);
                    System.out.println("---------------");

                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    alternate.loopC(i);
                    System.out.println("---------------");

                }
            }
        }, "C").start();
    }

}

class Alternate {

    private int number = 1; //当前正在执行线程的标记

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    //totalLoop 循环几轮的表示

    public void loopA(int totalLoop){
        lock.lock();
        try {

            //1 判断
            if(number!=1){
                condition1.await();
            }
            //2 执行打印操作
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + '\t' + i + "\t" + totalLoop );
            }
            //3 唤醒
            number = 2 ;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

  public void loopB(int totalLoop){
        lock.lock();
        try {

            //1 判断
            if(number!=2){
                condition2.await();
            }
            //2 执行打印操作
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + '\t' + i + "\t" + totalLoop );
            }
            //3 唤醒
            number = 3 ;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



    public void loopC(int totalLoop){
        lock.lock();
        try {

            //1 判断
            if(number!=3){
                condition3.await();
            }
            //2 执行打印操作
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + '\t' + i + "\t" + totalLoop );
            }
            //3 唤醒
            number = 1 ;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }







}
