package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 시간초과
public class BOJ7662_이중우선순위큐_시간초과 {
	static int T, Q;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			Q = Integer.parseInt(br.readLine());

			LinkedList<Integer> dq = new LinkedList<>();
			StringTokenizer st = null;
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				
				String input = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if(input.equals("I")) {
					dq.offer(num);
					
				} else if(input.equals("D") && !dq.isEmpty()) {
					if(num == -1) { // 최솟값 삭제
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
				Collections.sort(dq); // 오름차순 정렬	
			}
			
			if(dq.isEmpty()) sb.append("EMPTY").append("\n");
			else sb.append(dq.pollLast()).append(" ").append(dq.pollFirst());
		}
		
		System.out.println(sb);
	}
}