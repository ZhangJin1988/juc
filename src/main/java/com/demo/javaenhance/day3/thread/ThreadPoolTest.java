package com.demo.javaenhance.day3.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	private static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(3);
		//ExecutorService service = Executors.newCachedThreadPool();
		
		for(int i=0;i<10;i++){
			//��ϸƷζrunnable����ŵ�ѭ����������������Ϊ����ÿ���������Լ������ı��
			count = i;
			Runnable runnable = new Runnable(){
				private int mySequence = count;
				public void run() {
					for(int i=0;i<5;i++){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + 
								" is serving " + mySequence + " task,loop of " + i);
					}
				}
				
			};
			service.execute(runnable);
		}
	}

}
