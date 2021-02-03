package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636_치즈 {
	static int N, M, map[][], cheese, answer, dirs[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheese++;
			}
		}
		int time = 0;
		while (cheese != 0) {
			time++;
			answer = cheese;
			melt();
		}
		System.out.println(time);
		System.out.println(answer);
	}

	public static void melt() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {0, 0}); // 가장자리는 치즈가 X
		
		visited = new boolean[N][M];
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < dirs.length; i++) {
				int nx = cur[0] + dirs[i][0];
				int ny = cur[1] + dirs[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) 
					continue;
				
				if (map[nx][ny] == 1) { // 바깥과 닿아있는 치즈 녹이기
					cheese--;
					map[nx][ny] = 0;
				
				} else if (map[nx][ny] == 0) { // 여기도 바깥
					q.offer(new int[] { nx, ny });
				}
				visited[nx][ny] = true;
			}
		}
	}
}