package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트카페 {
	static int[][] d = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
	static int max;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= test; t++) {
			N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			for (int i = 0; i < map.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 0;

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					boolean[] visit = new boolean[101];
					dfs(0, i, j, map, visit, 0, i, j);
				}
			}
//					dfs(0, 0, 2, map, visit, 0, 0, 2);
			max = max == 0 ? -1 : max;
			sb.append("#").append(t).append(" ").append(max).append("\n");

		}
		System.out.println(sb);
	}

	private static void dfs(int index, int x, int y, int[][] map, boolean[] visit, int count, int startx, int starty) {
		
//		System.out.println(x+" "+y+" "+count);
		if (x == startx && y == starty&& count!=0) {
			max = max < count ? count : max;
			return;
		}
		if(index>=4)
			return;
		
		int dx = x;
		int dy = y;

		for (int i = 0; i < N; i++) {
			dx += d[index][0];
			dy += d[index][1];

			if (dx < 0 || dy < 0 || dx >= N || dy >= N || visit[map[dx][dy]]) {// 범위 벗어나거나 디저트 종류 같으면
				return;
			}

			visit[map[dx][dy]] = true;
			boolean[] visit_copy = visit.clone();
			dfs(index + 1, dx, dy, map, visit_copy, count + 1, startx, starty);
			count++;
		}
	}
}
