package com.demo.javaenhance.day2;

import com.demo.javaenhance.day1.ReflectPoint;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;


public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList collection1 = new ArrayList();
		collection1.add(1);
		collection1.add(1L);
		collection1.add("abc");
		//int i = (Integer)collection1.get(1);
		
		ArrayList<String> collection2 = new ArrayList<String>();
		//collection2.add(1);
		//collection2.add(1L);
		collection2.add("abc");
		String element = collection2.get(0);	
		
		//new String(new StringBuffer("abc"));
		Constructor<String> constructor1 = String.class.getConstructor(StringBuffer.class);
		String str2 = constructor1.newInstance(/*"abc"*/new StringBuffer("abc"));
		System.out.println(str2.charAt(2));		
		
		ArrayList<Integer> collection3 = new ArrayList<Integer>();
		System.out.println(collection3.getClass() == collection2.getClass());
		//collection3.add("abc");
		collection3.getClass().getMethod("add", Object.class).invoke(collection3, "abc");
		System.out.println(collection3.get(0));
		
		printCollection(collection3);
		
		//Class<Number> x = String.class.asSubclass(Number.class);
		Class<?> y;
		Class<String> x ;//Class.forName("java.lang.String");
		
		HashMap<String,Integer> maps = new HashMap<String, Integer>();
		maps.put("zxx", 28);
		maps.put("lhm", 35);
		maps.put("flx", 33);
		
		Set<Map.Entry<String,Integer>> entrySet = maps.entrySet();
		for(Map.Entry<String, Integer> entry : entrySet){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		add(3,5);
		Number x1 = add(3.5,3);
		Object x2 = add(3,"abc");
		
		swap(new String[]{"abc","xyz","itcast"},1,2);
		//swap(new int[]{1,3,5,4,5},3,4);
		
		Object obj = "abc";
		String x3 = autoConvert(obj);
		
		copy1(new Vector<String>(),new String[10]);
		copy2(new Date[10],new String[10]);		
		//copy1(new Vector<Date>(),new String[10]);
		
		GenericDao<ReflectPoint> dao = new GenericDao<ReflectPoint>();
		dao.add(new ReflectPoint(3,3));		
		//String s = dao.findById(1);
		
		//Vector<Date> v1 = new Vector<Date>();
		Method applyMethod = GenericTest.class.getMethod("applyVector", Vector.class);
		Type[] types = applyMethod.getGenericParameterTypes();
		ParameterizedType pType = (ParameterizedType)types[0];
		System.out.println(pType.getRawType());
		System.out.println(pType.getActualTypeArguments()[0]);
	}
	
	public static void applyVector(Vector<Date> v1){
		
	}

	
	private static <T> void fillArray(T[] a,T obj){
		for(int i=0;i<a.length;i++){
			a[i] = obj;
		}
	}
	private static <T> T autoConvert(Object obj){
		return (T)obj;
	}
	private static <T> void swap(T[] a,int i,int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private static <T> T add(T x,T y){
		return null;
	}
	
	public static void printCollection(Collection<?> collection){
		//collection.add(1);
		System.out.println(collection.size());
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
	
	public static <T> void printCollection2(Collection<T> collection){
		//collection.add(1);
		System.out.println(collection.size());
		for(Object obj : collection){
			System.out.println(obj);
		}

	}
	
	
	public static <T> void copy1(Collection<T> dest,T[] src){
		
	}
	
	public static <T> void copy2(T[] dest,T[] src){
		
	}	
}
