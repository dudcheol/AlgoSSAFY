/**
 * programmers - 2018 카카오 블라인드 채용. 비밀지도
 * SecretMap.java
 * @date 2020-08-17
 * @author hansolKim
 **/

package programmers;

import java.util.Arrays;

public class SecretMap {

	public static void main(String[] args) {
		SecretMap map = new SecretMap();
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		map.solution(5, arr1, arr2);
		
	}

	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = {};
		StringBuilder sb = new StringBuilder();
		answer = new String[n]; 
		
		for(int i=0; i<n; i++) {
			int n1 = arr1[i];
			int n2 = arr2[i];
			for(int j=(int) Math.pow(2.0, (double)(n-1)); j>=1; j/=2) {
				if(n1>=j || n2>=j) {
					
					if(n1>=j) { n1-=j; }
					if(n2>=j) { n2-=j; }
					System.out.println(n1+" "+n2);
					sb.append("#");
				} else {
					sb.append(" ");
				}
			}
			answer[i] = sb.toString(); 
			sb.delete(0, sb.length()); // stringbuilder 초기화
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}
}
