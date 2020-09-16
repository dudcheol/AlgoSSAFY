package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// swea 2117 홈 방범 서비스 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu
public class SW2117_홈방범서비스 {
	static int T, N, M, max;
	static int[] cost;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cost = new int[2*N + 1];
			calCost(); // 코스트 비용 초기화
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			 
			max = Integer.MIN_VALUE; 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			
			string.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(string);
	}

	// 사방탐색하면서 거리가 2인애들까지 안에 집이 있으면 +해주기
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][N];
		visited[i][j] = true;
		q.add(new int[] {i, j});
		
		int sum = 0;
		if(map[i][j] == 1)
			sum = 1;
		
		int dist = 1; // 중심부터의 거리 
		int qSize = 0;
		
		while(!q.isEmpty()) {
			qSize = q.size();
			
			if(sum*M - cost[dist] >= 0) { // 이익이 0보다 크면
				max = Math.max(max, sum); // 집 개수가 더 큰 걸 max값으로
			}
			
			while(qSize-- > 0) { // 중심에서부터 1씩 키우면서
				int[] point = q.poll();
				

				for (int d = 0; d < dirs.length; d++) {
					int nx = point[0] + dirs[d][0];
					int ny = point[1] + dirs[d][1];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;					
						q.add(new int[] {nx, ny});
						
						if(map[nx][ny] == 1) {
							sum++;
						}
					}
				}	
			}
			dist++; // K값
			

		}
	}
	static void calCost() {
		for (int K = 1; K <= 2*N; K++) {
			cost[K] = K*K + (K - 1)*(K - 1);
		}
	}

}
