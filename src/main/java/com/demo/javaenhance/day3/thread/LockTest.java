package com.demo.javaenhance.day3.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Business business = new Business();
		ExecutorService service = Executors.newFixedThreadPool(3);
		for(int i=0;i<3;i++){
			service.execute(new Runnable(){
				public void run(){
					business.service();
				}
			});
		}
		
		service.shutdown();
	}

}

class Business{
	private int count = 0;
	Lock lock = new ReentrantLock();
	public void service(){
		lock.lock();
		count++;
		try {
			Thread.sleep(10);
			System.out.println(count);		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
}
