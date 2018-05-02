package com.demo.javaenhance.day1;

public class AutoBox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer iObj = 3;
		System.out.println(iObj + 12);
		
		String s1 = new String("abc");
		String s2 = new String("abc");		
		Integer i1 = 137;
		Integer i2 = 137;
		

		System.out.println(i1 == i2);
		
		Integer i3 = Integer.valueOf(213);
		Integer i4 = Integer.valueOf(213);
		System.out.println(i3==i4);
		
	}

}
