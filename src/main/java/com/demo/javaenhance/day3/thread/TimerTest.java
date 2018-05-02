package com.demo.javaenhance.day3.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService service =  Executors.newScheduledThreadPool(3);
		service.scheduleAtFixedRate(
				new Runnable(){
					public void run() {
						System.out.println(Thread.currentThread().getName());
					}
					
				},
				10,
				1,
				TimeUnit.SECONDS);
		
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}
	}

}
