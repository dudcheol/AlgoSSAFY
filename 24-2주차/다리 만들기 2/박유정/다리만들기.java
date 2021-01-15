package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 다리만들기 {
	static int[][] map;
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int island = 0, N, M;
	static int min = Integer.MAX_VALUE;
	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					visit[i][j] = true;
					map[i][j] = ++island;
					island(visit, i, j);
				}
			}
		}
		int[][] minlen = new int[island + 1][island + 1];// 다리연결 저장할 배열
		for (int i = 0; i < minlen.length; i++) {
			Arrays.fill(minlen[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < N; i++) {// 연결할 수 있는 모든 다리 연결
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					bridge(minlen, i, j);
				}
			}
		}
//		print(map);
//		print(minlen);
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];// 다리길이로 정렬
			}
		});

		int answer = 0;
		boolean[] visited = new boolean[island + 1];
		q.add(new int[] { 1, 0 });// 현재 노드,다리 길이
		int cnt = 0;// visted 노드수

		while (!q.isEmpty()) {// 최소 연결 찾기
			int[] arr = q.poll();
			int node = arr[0];
			if (visited[node])// 방문한 섬이면 pass
				continue;
			cnt++;
			answer += arr[1];
			if (cnt == island) {// 모든 섬 다 방문했으면
				break;
			}

			visited[node] = true;

			for (int i = 1; i < island + 1; i++) {
				if (minlen[node][i] != Integer.MAX_VALUE && !visited[i]) {
					q.add(new int[] { i, minlen[node][i] });// 연결된 섬 모두 넣어주기
				}
			}
		}
		if (cnt != island)// 모든 섬이 다 연결안됐으면
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	private static void bridge(int[][] minlen, int x, int y) {// 가능한 다리모두연결
		int node = map[x][y];

		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 10; j++) {
				int dx = x + j * d[i][0];
				int dy = y + j * d[i][1];

				if (dx < 0 || dy < 0 || dx >= N || dy >= M || map[dx][dy] == node)// 같은 섬인경우
					break;
				else if (map[dx][dy] == 0)
					continue;
				else if (j < 3)
					break;
				if (minlen[map[dx][dy]][node] > j - 1) {// 현재다리가 더짧음
					minlen[map[dx][dy]][node] = j - 1;
					minlen[node][map[dx][dy]] = j - 1;
				}
				break;
			}
		}
	}

	private static void island(boolean[][] visit, int x, int y) {// 섬 번호매기기dfs
		for (int i = 0; i < 4; i++) {
			int dx = x + d[i][0];
			int dy = y + d[i][1];

			if (dx < 0 || dy < 0 || dx >= N || dy >= M || visit[dx][dy] || map[dx][dy] == 0)
				continue;

			visit[dx][dy] = true;
			map[dx][dy] = island;

			island(visit, dx, dy);
		}
	}
//	private static void print(int[][] map2) {
//	for (int i = 0; i < map2.length; i++) {
//		System.out.println(Arrays.toString(map2[i]));
//	}
//	System.out.println();
//}
}
