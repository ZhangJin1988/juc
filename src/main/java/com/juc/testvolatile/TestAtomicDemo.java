package com.juc.testvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjin
 * @since 2017/5/24
 * <p>
 * 1. i++ 的原子性问题  ： i++ 的操作实际上分为三个步骤 "读 -- 改 -- 写"
 *
 *
 * 2， 原子变量 jdk1.5以后  java.util.concurrent.atomic 包下面提供了常用的原子变量
 *
 *   001， volatile 的变量 保证了内存可见性
 *   002   CAS算法 保证数据的原子性  Compare——And——Swap 操作系统
 *              CAS算法 算法是硬件对于并发操作共享数据的支持
 *              CAS 包含了三个操作数
 *                  内存值 V
 *                  预估值 A
 *                  更新值 B
 *                  当且仅当 V == A 时 ， V = B 否则将不做任何操作
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {

//    private  int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
//        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber() );
        System.out.println(getSerialNumber());
    }

//

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

    public void setSerialNumber(AtomicInteger serialNumber) {
        this.serialNumber = serialNumber;
    }
}