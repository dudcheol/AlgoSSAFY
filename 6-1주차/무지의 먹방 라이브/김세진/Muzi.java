package de;

import java.util.ArrayList;
import java.util.HashMap;

public class Muzi {

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
	
	
	   public static int solution(int[] food_times, long k) {
			int index = 0;
			int lastIndex = food_times.length;
			
			while (k > 0) {
				
				
				if (food_times[index] == 0) {
					index++;
					continue;
					
				} else {
					food_times[index] -= 1;
					k-=1;
				}
				
				index++;
				
				if (index == lastIndex) {
					index = 0;
				}
			}

			

			return index+1;
	    }
}
