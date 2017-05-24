package com.juc.testForkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author zhangjin
 * @since 2017/5/24
 */
public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant now = Instant.now();


        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0,10000000l);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("耗费的时间是：" + Duration.between(now,end).toMillis());

    }

    @Test
    public void test1(){
        Instant now = Instant.now();

        long sum = 0l;
        for (long i = 0; i < 10000000l; i++) {
            sum += i;

        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费的时间是：" + Duration.between(now,end).toMillis());
    }


    //java8新特性
    @Test
    public void test2(){
        Instant now = Instant.now();

        long sum = 0l;

        sum = LongStream.rangeClosed(0l,10000000l).parallel().reduce(0l,Long::sum);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费的时间是：" + Duration.between(now,end).toMillis());
    }
}

class  ForkJoinSumCalculate extends RecursiveTask<Long>{

    private int start;
    private long end;

    private static final long THURSHOLD = 10000l;


    public ForkJoinSumCalculate(int start, long end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {

        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0l;

            for (long i = start; i <end ; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end)/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,middle);
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate((int) (middle+1),end);
            right.fork();
//            left.join();
            return right.join();

        }

    }
}