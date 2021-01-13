/**
 * Backjoon - 2812. 크게 만들기
 * 크게만들기_2812.java
 * @date 2021-01-13
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 크게만들기_2812 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String number = br.readLine();
		
		Stack<Integer> s = new Stack<>();
		for(int i=0; i<N; i++) {
			int cur = number.charAt(i)-'0';
			while(!s.isEmpty() && K>0 && s.peek() < cur) {
				s.pop();
				K--;
			}
			
			s.add(cur);
		}
		
		while(K>0) {
			s.pop();
			K--;
		}
		
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		
		System.out.println(sb.reverse().toString());
		br.close();

	}
}