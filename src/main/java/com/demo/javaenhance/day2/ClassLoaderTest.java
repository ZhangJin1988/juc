package com.demo.javaenhance.day2;

import java.util.Date;

public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(
				ClassLoaderTest.class.getClassLoader().getClass().getName()
				);
		System.out.println(
				System.class.getClassLoader()
				);
		System.out.println("xxx");
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while(loader != null){
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);
		
		//System.out.println(new ClassLoaderAttachment().toString());
		System.out.println("xxx2");
		Class clazz = new MyClassLoader("itcastlib").loadClass("cn.itcast.day2.ClassLoaderAttachment");
		Date d1 =  (Date)clazz.newInstance();
		System.out.println(d1);
	}

}
