package com.demo.javaenhance.day3.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		final Business2 business = new Business2();
		service.execute(new Runnable(){

			public void run() {
				for(int i=0;i<50;i++){
					business.sub();
				}
			}
			
		});
		
		for(int i=0;i<50;i++){
			business.main();
		}
	}

}

class Business2{
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	boolean bShouldSub = true;
	public void sub(){
		lock.lock();
		if(!bShouldSub)
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try
		{
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			bShouldSub = false;
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public void main(){
		lock.lock();
		if(bShouldSub)
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		try
		{
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			bShouldSub = true;
			condition.signal();			
		}finally{
			lock.unlock();
		}		
	}
}