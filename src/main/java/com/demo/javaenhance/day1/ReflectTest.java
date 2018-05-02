package com.demo.javaenhance.day1;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String str1 = "abc";
		Class cls1 = str1.getClass();
		Class cls2 = String.class;
		Class cls3 = Class.forName("java.lang.String");
		System.out.println(cls1 == cls2);
		System.out.println(cls1 == cls3);
		
		System.out.println(cls1.isPrimitive());
		System.out.println(int.class.isPrimitive());
		System.out.println(int.class == Integer.class);
		System.out.println(int.class == Integer.TYPE);
		System.out.println(int[].class.isPrimitive());
		System.out.println(int[].class.isArray());		
		
		//new String(new StringBuffer("abc"));
		Constructor constructor1 = String.class.getConstructor(StringBuffer.class);
		String str2 = (String)constructor1.newInstance(/*"abc"*/new StringBuffer("abc"));
		System.out.println(str2.charAt(2));
		
		ReflectPoint pt1 = new ReflectPoint(3,5);
		Field fieldY = pt1.getClass().getField("y");
		//fieldY��ֵ�Ƕ��٣���5,��fieldY���Ƕ������ϵı������������ϣ�Ҫ����ȥȡĳ�������϶�Ӧ��ֵ
		System.out.println(fieldY.get(pt1));
		Field fieldX = pt1.getClass().getDeclaredField("x");
		fieldX.setAccessible(true);
		System.out.println(fieldX.get(pt1));	
		
		changeStringValue(pt1);
		System.out.println(pt1);
		
		Method methodCharAt = String.class.getMethod("charAt", int.class);
		System.out.println(methodCharAt.invoke(str1, 1));
		System.out.println(methodCharAt.invoke(str1, new Object[]{2}));
		
		//TestArguments.main(new String[]{"111","222","333"});
		String startingClassName = args[0];
		Method mainMethod = Class.forName(startingClassName).getMethod("main", String[].class);
		//mainMethod.invoke(null, new Object[]{new String[]{"111","222","333"}});
		mainMethod.invoke(null, (Object)new String[]{"111","222","333"});
		
		int [] a1 = new int[]{1,2,3};
		int [] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String [] a4 = new String[]{"a","b","c"};
		System.out.println(a1.getClass() == a2.getClass());
//		System.out.println(a1.getClass() == a4.getClass());
//		System.out.println(a1.getClass() == a3.getClass());
		System.out.println(a1.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());
		System.out.println(a4.getClass().getSuperclass().getName());
		
		Object aObj1 = a1;
		Object aObj2 = a4;
		//Object[] aObj3 = a1;
		Object[] aObj4 = a3;
		Object[] aObj5 = a4;
		
		System.out.println(a1);
		System.out.println(a4);
		System.out.println(Arrays.asList(a1));
		System.out.println(Arrays.asList(a4));	
		
		printObject(a4);
		
		printObject("xyz");
	}

	private static void printObject(Object obj) {
		Class clazz = obj.getClass();
		if(clazz.isArray()){
			int len = Array.getLength(obj);
			for(int i=0;i<len;i++){
				System.out.println(Array.get(obj, i));
			}
		}else{
			System.out.println(obj);
		}
		
	}

	private static void changeStringValue(Object obj) throws Exception {
		Field[] fields = obj.getClass().getFields();
		for(Field field : fields){
			//if(field.getType().equals(String.class)){
			if(field.getType() == String.class){
				String oldValue = (String)field.get(obj);
				String newValue = oldValue.replace('b', 'a');
				field.set(obj, newValue);
			}
		}
		
	}

}


class TestArguments{
	public static void main(String[] args){
		for(String arg : args){
			System.out.println(arg);
		}
	}
}
