package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2117_홈방범서비스 {
	static int N, M; // 도시크기, 하나의 집이 지불할 수 있는 비용 // 5 ≤ N ≤ 20
	static int[][] map;
	static ArrayList<int[]> houses;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static Queue<int[]> q;
	static boolean[][] visited;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			houses = new ArrayList<>();
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						houses.add(new int[] { i, j });
					}
				}
			}

			// 마름모모양 => 무조건 BFS!! BFS가 다이아몬드 스텝이 가능함 (이거 하나만 알고있어도 크게 도움이 된다!!!)
			q = new LinkedList<int[]>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					q.offer(new int[] {i, j});
					visited[i][j] = map[i][j] == 1;
					bfs(map[i][j]);
					q.clear();
					visited = new boolean[N][N];
				}
			}

			sb.append('#').append(test_case).append(' ').append(max == Integer.MIN_VALUE ? 1 : max).append('\n');
		}
		System.out.print(sb);

	}

	private static void bfs(int cnt) {

		int K = 1; // 서비스 영역의 면적
		int houseCnt = cnt; // 집이 있는 칸에서 시작
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- != 0) {
				int[] polled = q.poll();
				int y = polled[0];
				int x = polled[1];

				// 큐 퍼뜨려 나가면서 집 확인하기
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;

					if (map[ny][nx] == 1)
						houseCnt++;

					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
				}

			} // end of size while
			// 서비스 전용면적 계산하기
			K++;
			if(K == 2*N) break;
			if (calc(K, houseCnt) >= 0) { // 손해를 보지 않음
				max = Math.max(max, houseCnt);
			}
		} // end of q empty
	} // end of bfs

	private static int calc(int K, int houseCnt) {
		return (houseCnt * M) - (K * K + (K - 1) * (K - 1));
	}

}
