package com.demo.javaenhance.day3.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(new Runnable(){
			public void run() {
				System.out.println("I dead");
			}		
			
		});
		//���û������Ĵ��룬����һֱ���ڲ�����״̬��
		service.shutdown();
		//service.shutdownNow();
	}

}
