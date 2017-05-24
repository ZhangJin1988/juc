package com.juc.testvolatile;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangjin
 * @since 2017/5/24
 *
 * CopyOnWriteArrayList  写入并复制   每次写入都会复制一个新的列表
 *
 * 有并发迭代操作的时候 就可以用这个集合容器
 *   注意           添加操作多的时候 效率低  因为每次添加的时候 都会进行一次复制 所以开销比较大
 *                  并发迭代操作多的时候 可以用
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {


        HelloThread ht = new HelloThread();
        for (int i = 0; i < 10 ; i++) {
            new Thread(ht).start();

        }
    }

}

class HelloThread implements Runnable{

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("AA");
        }

    }
}
