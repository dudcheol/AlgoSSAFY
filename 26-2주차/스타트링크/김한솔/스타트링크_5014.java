/**
 * Backjoon - 5014. 스타트링크
 * 스타트링크_5014.java
 * @date 2021-01-29
 * @author hansolKim
 **/

package p5000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크_5014 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); // 건물의 총 층수
		int S = Integer.parseInt(st.nextToken()); // 강호의 현재 층 수
		int G = Integer.parseInt(st.nextToken()); // 목표 층 수
		int U = Integer.parseInt(st.nextToken()); // 위로 U층
		int D = Integer.parseInt(st.nextToken()); // 아래로 D층
				
		boolean[] visited = new boolean[F+1];
		int[] dx = new int[2];
		
		dx[0] = U; dx[1] = -D;
		
		int answer = S == G ? 0 : -1;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {S, 0});
		visited[S] = true;
				
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int t = q.poll()[1];
			
			for(int i=0; i<2; i++) {
				int nx = x+dx[i];
				
				// 0층이거나 건물 최고층수보다 높이올라간 경우 또는 이미 가본 층수인경우 패스
				if(nx<=0 || nx>F || visited[nx]) continue;
				
				if(nx == G) {
					answer = t+1;
					break;
				}
				
				visited[nx] = true;
				q.add(new int[] {nx, t+1});
			}
			
		}
		
		System.out.println(answer == -1 ? "use the stairs" : answer);
		br.close();
	}
}