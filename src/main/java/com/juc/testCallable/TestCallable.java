package com.juc.testCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhangjin
 * @since 2017/5/24
 * 创建线程的方式三 ： 实现Callable接口
 *          call 方法 有异常 并且 可以有返回值  可以抛出异常
 *        //执行 Callable 方式 需要 FutureTask 实现类的支持 用于接收运算结果。
            FutureTask 是 Future接口的实现类
        当线程阻塞的时候 结果就没有获取  FutureTask 也有点类似闭锁  等到线程执行完 才会向下执行
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1执行 Callable 方式 需要 FutureTask 实现类的支持 用于接收运算结果。
        ThreadDemo td = new ThreadDemo();
        FutureTask<Integer> result = new FutureTask<Integer>(td);
        new Thread(result).start();
        //2 接收线程运算后的结果
        Integer sum = result.get();//FutureTask 也可以用于闭锁
        System.out.println(sum);
        System.out.println("--------------------");
    }

}

class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        int sum = 0;
        for (int i = 0; i <= 100000 ; i++) {
//            System.out.println(i);
            sum += i;

        }
        return sum;

    }
}
