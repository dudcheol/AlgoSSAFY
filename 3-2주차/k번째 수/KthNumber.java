package programmers;

import java.util.Arrays;

public class KthNumber {
	static int[] array = { 1, 5, 2, 6, 3, 7, 4 };
	static int[][] command = { {2, 5, 3}, {4, 4, 1}, {1, 7, 3} };
	
	public static void main(String[] args) {
		int[] answer = {};
		int start, end, k;
		int[] temp;
		
		answer = new int[command.length];
		
		for (int i = 0; i < command.length; i++) {
			start = command[i][0];
			end = command[i][1];
			k = command[i][2];
			
			int index = 0;
			temp = new int[end - start + 1];
			for (int j = start; j <= end; j++) {
				temp[index++] = array[j - 1];
			}
			Arrays.sort(temp);
			answer[i] = temp[k - 1];
		}
		
	}

}
