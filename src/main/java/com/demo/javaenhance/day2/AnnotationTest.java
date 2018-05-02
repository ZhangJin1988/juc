package com.demo.javaenhance.day2;

import java.lang.reflect.Method;


@ItcastAnnotation(annotationAttr=@MetaAnnotation("flx"),color="red",value="abc",arrayAttr=1)
public class AnnotationTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	@ItcastAnnotation("xyz")
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.runFinalizersOnExit(true);
		if(AnnotationTest.class.isAnnotationPresent(ItcastAnnotation.class)){
			ItcastAnnotation annotation = (ItcastAnnotation)AnnotationTest.class.getAnnotation(ItcastAnnotation.class);
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.arrayAttr().length);
			System.out.println(annotation.lamp().nextLamp().name());
			System.out.println(annotation.annotationAttr().value());
		}
		
		Method mainMethod = AnnotationTest.class.getMethod("main", String[].class);
		ItcastAnnotation annotation2 = (ItcastAnnotation)mainMethod.getAnnotation(ItcastAnnotation.class);
		System.out.println(annotation2.value());
	}

	@Deprecated
	public static void sayHello(){
		System.out.println("hi,���ǲ���");
	}
}
