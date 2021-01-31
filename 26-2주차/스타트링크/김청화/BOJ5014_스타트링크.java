package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5014_스타트링크 {
	static int F, S, G, U, D, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken()); // 강호가 있는 층
		G = Integer.parseInt(st.nextToken()); // 스타트링크
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		boolean flag = true;
		int diff = G - S;
		
		if(diff == 0) // 처음부터 같은 층
			answer = 0;
		
		else if(diff > 0) { // 스타트링크가 더 높은 층
			if(U == 0) {
				System.out.println("use the stairs");
				return;
			}
			
			while(S < G) { // 강호가 더 높은 층으로 갈 때까지 이동
				S += U;
				answer++;
			}
			
			while(S > G) { // 더 올라간 만큼 밑으로
				S -= D;
				answer++;
			}
			
			if(S != G) flag = false; // 같은 층에 없다면 false
			
		} else { // 스타트링크가 더 낮은 층
			if(D == 0) {
				System.out.println("use the stairs");
				return;
			}
			
			while(S > G) {
				S -= D;
				answer++;
			}
			
			while(S < G) {
				S += U;
				answer++;
			}
			
			if(S != G) flag = false;
		}
		
		if(!flag)
			System.out.println("use the stairs");
		else
			System.out.println(answer);
	}
}