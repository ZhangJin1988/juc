package com.demo.javaenhance.day3.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TraditionalThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		}.start();
		
		new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}			
		}).start();
		
		
		new Timer().schedule(
				new TimerTask(){
					public void run() {
						System.out.println(Thread.currentThread().getName());
					}
				}, 
				10000,
				1000);
	}

}
