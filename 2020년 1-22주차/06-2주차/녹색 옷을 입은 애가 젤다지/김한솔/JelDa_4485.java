/**
 * Backjoon - 4485. 녹색 옷 입은 애가 젤다지
 * JelDa_4485.java
 * @date 2020-09-05
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class JelDa implements Comparable<JelDa>{
	int x;
	int y;
	int cost;
	
	public JelDa(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(JelDa o) { 
		return this.cost - o.cost; // 오름차순 정렬
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCost() {
		return cost;
	}
	
}

public class JelDa_4485 {

	private static int[][] map;
	private static int[][] cost; 
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 1;
		
		while(true) {
			
			N = Integer.parseInt(br.readLine()); // 맵 크기 입력
			if(N == 0) {break;} // 0입력시 종료
			
			int answer = 0;
			map = new int[N][N];
			cost = new int[N][N];
			
			for(int i=0; i<N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			
			sb.append("Problem ").append(T++).append(": ");
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
 			}
			
			cost[0][0] = map[0][0];
			dijkstra();
			
			answer = cost[N-1][N-1];
			sb.append(answer).append("\n");
		}
		System.out.println(sb); // 결과 출력
	}

	private static void dijkstra() {
		
		PriorityQueue<JelDa> pq = new PriorityQueue<>();

		// 시작점 우선순위 큐에 삽입
		pq.offer(new JelDa(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			
			JelDa j = pq.poll();
			int x = j.getX();
			int y = j.getY();			
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx <0 || nx >= N || ny < 0 || ny >= N) continue; // 영역 밖인 경우
				
				if(cost[x][y] + map[nx][ny] < cost[nx][ny]) { // 해당 정점에서 갈 수 있는 정점과의 거리 값이 더 짧은 경우 갱신
					cost[nx][ny] = cost[x][y] + map[nx][ny]; 
					pq.offer(new JelDa(nx, ny, map[nx][ny]));
				}
			}
				
		}
	}

}
