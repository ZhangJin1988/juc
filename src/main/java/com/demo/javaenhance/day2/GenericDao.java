package com.demo.javaenhance.day2;

import java.util.Set;

//dao data access object--->crud
public class GenericDao<E>  {
	public void add(E x){
		
	}
	
	public E findById(int id){
		return null;
	}
	
	public void delete(E obj){
		
	}
	
	public void delete(int id){
		
	}	
	
	public void update(E obj){
		
	}
	
	public static <E> void update2(E obj){
		
	}
	
	public E findByUserName(String name){
		return null;
	}
	public Set<E> findByConditions(String where){
		return null;
	}
}
