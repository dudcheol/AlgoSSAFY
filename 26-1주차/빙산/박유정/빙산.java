package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 빙산 {
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 주변에 바다가있으면 빨리녹음
		int year = 0;

		while (true) {
			boolean[][] visit = new boolean[N][M];
			year++;
			// 빙하 녹음
			melting(map);

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0 && !visit[i][j]) {
						dfs(map, i, j, visit);// 덩어리 세줌
						cnt++;
					}
				}
			}
			if (cnt > 1)// 분리되면 나감
				break;
			else if (cnt == 0) {// 모두 녹았음
				year = 0;
				break;
			}

		}
		System.out.println(year);
	}

	private static void dfs(int[][] map, int x, int y, boolean[][] visit) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int dx = x + d[i][0];
			int dy = y + d[i][1];
			if (dx < 0 || dy < 0 || dx >= N || dy >= M || map[dx][dy] == 0 || visit[dx][dy])
				continue;
			dfs(map, dx, dy, visit);
		}
	}

	private static void melting(int[][] map) {
		int[][] copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;

				int count = 0;
				// 바다가 네방향에 몇개있는지 센다.
				for (int k = 0; k < 4; k++) {
					int dx = i + d[k][0];
					int dy = j + d[k][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= M)
						continue;
					if (map[dx][dy] == 0)
						count++;
				}
				// 바다만큼 녹음
				copy[i][j] = map[i][j] <= count ? 0 : map[i][j] - count;
			}
		}
		for (int i = 0; i < N; i++) {
			map[i] = copy[i];
		}
	}
}
