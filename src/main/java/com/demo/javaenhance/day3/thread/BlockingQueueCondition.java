package com.demo.javaenhance.day3.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueCondition {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		final Business3 business = new Business3();
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

class Business3{
	BlockingQueue subQueue = new ArrayBlockingQueue(1);
	BlockingQueue mainQueue = new ArrayBlockingQueue(1);
	{
		try {
			mainQueue.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void sub(){
		try
		{
			mainQueue.take();
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			subQueue.put(1);
		}catch(Exception e){

		}
	}
	
	public void main(){
		
		try
		{
			subQueue.take();
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			mainQueue.put(1);
		}catch(Exception e){
		}		
	}
}
