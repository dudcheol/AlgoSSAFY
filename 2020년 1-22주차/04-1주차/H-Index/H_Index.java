package programmers;

import java.util.Arrays;

public class H_Index {
	//static int[] citations = { 6, 6, 6, 6, 6, 1 };
	//static int[] citations = { 20, 21, 22, 23 };
	static int[] citations = { 4, 4, 4 };
	
	public static void main(String[] args) {
		int answer = 0;
		
		int n = citations.length; // 논문 n편
		int h;
		int high = 0;
		Arrays.sort(citations);
		// 0, 1, 3, 5, 6
		
		// 발표한 논문만큼 탐색
		for (int i = citations.length - 1; i >= 0; i--) {
			h = citations[i]; // 0
			high = n - i; // 5 - 0 = 5
			
			// h번 이상 인용된 논문 h 이상
			if (high >= h) { // 이 순간 최대가 될 수 없음
				break;
			}
			answer++; // 내림차순으로 탐색했기 때문에 가장 큰 h가 answer++되고 최종
		}
	        
		System.out.println(answer);
	}
}
