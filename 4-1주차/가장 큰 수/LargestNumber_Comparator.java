package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber_Comparator {
	
	static int[] numbers = { 3, 30, 34, 5, 9 };
	
	public static void main(String[] args) {
        String answer = "";
        
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = (String.valueOf(numbers[i]));
        }
        
        // Comparator: 일반적이지 않은 기준으로 정렬, 2개를 더하여 더 큰 쪽이 우선순위가 있도록 정렬
        Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2); // 내림차순
			}      	
        };
        
        Arrays.sort(arr, comparator);
        
        for (int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }       
        
        // 정렬 후 0으로 시작할 경우 -> 0 return
        if (arr[0].equals("0")) 
        	answer = "0";
        
        System.out.println(answer);
	}
}
