package com.demo.javaenhance.day1;

import java.util.Date;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeekDay1 weekDay = WeekDay1.MON;
		System.out.println(weekDay.nextDay());
		
		WeekDay weekDay2 = WeekDay.FRI;
		System.out.println(weekDay2);
		System.out.println(weekDay2.name());
		System.out.println(weekDay2.ordinal());	
		System.out.println(WeekDay.valueOf("SUN").toString());
		System.out.println(WeekDay.values().length);
		
		new Date(300){};
	}

	public enum WeekDay{

		SUN(1),MON(),TUE,WED,THI,FRI,SAT;
		private WeekDay(){System.out.println("first");}
		private WeekDay(int day){System.out.println("second");}
	}
	
	public enum TrafficLamp{
		RED(30){
			public  TrafficLamp nextLamp(){
				return GREEN;
			}
		},
		GREEN(45){
			public  TrafficLamp nextLamp(){
				return YELLOW;
			}			
		},
		YELLOW(5){
			public  TrafficLamp nextLamp(){
				return RED;
			}			
		};
		public abstract TrafficLamp nextLamp();
		private int time;
		private TrafficLamp(int time){this.time = time;}
	}
}
