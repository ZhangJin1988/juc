package com.demo.javaenhance.day1;

public class VarableParameter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(add(2,3));
		System.out.println(add(2,3,5));		
	}
	
	
	public static int add(int x,int... args){
		int sum = x;
/*		for(int i=0;i<args.length;i++){
			sum += args[i];
		}*/
		
		for(int arg : args){
			sum += arg;
		}
		return sum;
	}

}
