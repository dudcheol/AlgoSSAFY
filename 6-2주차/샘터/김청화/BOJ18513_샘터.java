package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18513_샘터 {
	static int N, K;	
	static final int INDEX = 100000000;
	static boolean[] space = new boolean[INDEX*2 + 1];

 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받음
		StringTokenizer st = new StringTokenizer(br.readLine()); // stringtokenizer로 공백 구분
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Queue<Position> q = new LinkedList<Position>();	
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken()); // 샘터 위치 입력받음
			q.add(new Position(x + INDEX + 1, 1)); // 큐에 샘터 +1 위치 저장
			q.add(new Position(x + INDEX - 1, 1)); // 큐에 샘터 -1 위치 저장
			space[x + INDEX] = true; // 샘터위치는 true로
		}

		long sum = 0; // 샘터& 집과의 거리 더한 값
		int cnt = 0; // 여태까지 지은 집의 개수
		boolean flag = false; // 여태까지 지은 집의 개수가 K개인지 판단하는 플래그
		
		while(!q.isEmpty()) { 
			if(cnt == K) break;
			
			Position tmp = q.poll(); // 큐에 저장된 건물을 하나 꺼냄
			int x = tmp.x; // 현재 볼 건물의 위치
			int len = tmp.length; // 현재 건물과 샘터와의 거리
			
			if(!space[x]) { // x 위치에 건물 지을 수 있으면 
				space[x] = true;
				sum += len;
				cnt++;
				q.offer(new Position(x + 1, len + 1));
				q.offer(new Position(x - 1, len + 1));
			}
		}
		System.out.println(sum); // 행복지수 출력
	}
 
 	static class Position{
 		int x; // 해당 건물의 위치
 		int length; // 샘터와의 거리
 		
 		Position(int x, int length){
 			this.x = x;
 			this.length = length;
 		}
 	}
}