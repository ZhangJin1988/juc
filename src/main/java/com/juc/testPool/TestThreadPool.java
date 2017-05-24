package com.juc.testPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhangjin
 * @since 2017/5/24
 * 1 线程池 提供了一个线程队列 队列中 保持着所有等待状态的线程 避免了创建与销毁的额外开销
 * 2 线程池的体系结构
 * java.util.concurrent.Executor 负责线程的使用与调度的根接口
 * |-- ExecutorService 子接口 线程池的主要接口
 * |-- ThreadPoolExecutor 实现类 线程池的实现类
 * |-- ScheduleExecutorService 子接口 负责线程的调度的
 * |-- ScheduledThreadPoolExecutor 实现类 ： 继承ThreadPoolExecutor 实现了ScheduleExecutorService
 * <p>
 * 3 工具类 ： Executors
 * ExecutorService newFixedThreadPool() ; 创建固定大小的线程池
 * ExecutorService newCachedThreadPool():缓存线程池 线程池的数量不固定 可以根据需求自动的更改数量
 * ExecutorService newSingleThreadExecutor():创建单个线程池 线程池只有一个线程
 * ScheduledExecutorService newScheduledThreadPool(): 创建固定大小的线程池 可以延时或者定时的执行任务
 */
public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1,创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += i;

                    }
                    return sum;
                }
            });
            list.add(future);

        }

        for(Future future : list){
            System.out.println(future.get());
        }


        pool.shutdown();

//        ThreadPoolDemo tpd = new ThreadPoolDemo();

//        new Thread(tpd).start();
//        new Thread(tpd).start();
        //2.为线程池中分配任务

//        for (int i = 0; i < 10; i++) {
//            pool.submit(tpd);
//        }
//
//
//
//        //3,关闭线程池
//        pool.shutdown();

    }
}

class ThreadPoolDemo implements Runnable {

    private int i = 0;

    @Override
    public void run() {

        while (i <= 100) {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}
