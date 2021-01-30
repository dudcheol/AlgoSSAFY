package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스타트링크 {
	static int F, S, G, U, D;
	static int max = Integer.MAX_VALUE;
	static int[] visit=new int[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());// 건물높이
		S = Integer.parseInt(st.nextToken());// 현재 위치
		G = Integer.parseInt(st.nextToken());// 목적지
		U = Integer.parseInt(st.nextToken());// 위로
		D = Integer.parseInt(st.nextToken());// 아래로
		Arrays.fill(visit, max);
		elevator(S, 0);
		if (visit[G] == max)
			System.out.println("use the stairs");
		else
			System.out.println(visit[G]);
	}

	private static void elevator(int cur, int cnt) {
		if (visit[cur]<=cnt)//가지치기
			return;
		
		visit[cur]=cnt;
		
		if (cur == G) //목적지에 도착
			return;
		
		if(cur+U<=F)
			elevator(cur + U, cnt + 1);//올라가기
		if(cur-D>=1)
			elevator(cur - D, cnt + 1);//내려가기
	}

}
