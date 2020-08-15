package programmers;

import java.util.Arrays;
import java.util.Collections;

public class LargestNumber {
	//static int[] numbers = { 6, 10, 2 };
	//static int[] numbers = { 3, 30, 34, 5, 9 };
	static int[] numbers = { 0, 0, 0};
	
	public static void main(String[] args) {
		String answer = "";

		String[] nums = new String[numbers.length];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = String.valueOf(numbers[i]);
		}

		// 사전 순으로 정렬한 뒤 붙이기
		Arrays.sort(nums, Collections.reverseOrder());
		answer += nums[0];
		for (int i = 1; i < nums.length; i++) {			
			// 사전 순으로 정렬했는데 0이 연속으로 나올 경우 붙이는 것이 의미 없음
			if(nums[i - 1].equals("0") && nums[i].equals("0")) {
				break;
			}
			answer += nums[i];
		}
	}


}
