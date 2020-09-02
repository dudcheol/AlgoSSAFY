package de;

import java.util.ArrayList;
import java.util.HashMap;

public class Muzi2 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
		System.out.println(solution(new int[] { 1,1,1,1 }, 4));

	}

	static class Food{
		int index;
		int value;
		
		public Food(int index, int value) {
			this.index = index;
			this.value = value;
		}
		
	}
	
	
	static public int solution(int[] food_times, long k) {
		int index = 0;
		int lastIndex = food_times.length-1;
		
		ArrayList<Food> list=new ArrayList<Food>();
//		HashMap<Integer, Integer> map =new HashMap<Integer, Integer>();
		
		
		for(int i=0;i<food_times.length;i++) {
			list.add(new Food(i,food_times[i]));
			
		}
		
		Food food=null;
		
		
		
		while(k>0) {
			
		}
		
		
		return index+1;
	}
}
