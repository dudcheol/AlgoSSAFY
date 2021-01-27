package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
	static int N, M, answer, map[][], dirs[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	static boolean[][] visited, checked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		checked = new boolean[N][M];
		
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					checked[i][j] = true;
					q.add(new int[] {i, j});
				}
			}
		}
		
		while(true) {
			answer++;
			
			if(q.isEmpty()) {
				System.out.println(0); 
				return;
			}
			bfs(q); // 녹이기
			
			// 개수 세기
			visited = new boolean[N][M];
			checked = new boolean[N][M];
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] > 0) {
						checked[i][j] = true;
						q.add(new int[] {i, j});
					}
					if(!visited[i][j] && map[i][j] > 0) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			if(cnt >= 2) {
				System.out.println(answer);
				break;
			}
		}
	}

	private static void dfs(int x, int y) {
		
		visited[x][y] = true;
		
		for (int d = 0; d < dirs.length; d++) {
			int nx = x + dirs[d][0];
			int ny = y + dirs[d][1];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;
			
			if(map[nx][ny] > 0) 
				dfs(nx, ny);
		}
	}

	private static void bfs(Queue<int[]> q) {
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < dirs.length; d++) {
				int nx = cur[0] + dirs[d][0];
				int ny = cur[1] + dirs[d][1];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(map[nx][ny] <= 0 && !checked[nx][ny])
					map[cur[0]][cur[1]]--;
			}
		}
	}
}