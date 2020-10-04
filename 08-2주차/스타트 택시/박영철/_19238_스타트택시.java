package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 1시간 40분 */
public class _19238_스타트택시 {
	static final int PASNGER_ON = 0;
	static final int PASNGER_OFF = 1;
	static int N, M, Oil;// M명 승객
	static int[][] map;// NxN map
	static int[] dy = { -1, 1, 0, 0 };// 상하좌우 이동
	static int[] dx = { 0, 0, -1, 1 };// 상하좌우 이동
	static int tY;
	static int tX;
	static ArrayList<int[]> pasngerStart;
	static ArrayList<int[]> pasngerDest;
	static PriorityQueue<Point> pq;
	static int state;
	static int dest;
	static int[][] Dist;

	static class Point implements Comparable<Point> {
		int y;
		int x;
		int dist;
		int oil;

		public Point(int y, int x, int dist, int oil) {
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.oil = oil;
		}

		@Override
		public int compareTo(Point o) {
			int res = Integer.compare(this.dist, o.dist);
			if (res == 0) {
				int res2 = Integer.compare(this.y, o.y);
				if (res2 == 0) {
					return Integer.compare(this.x, o.x);
				}
				return res2;
			}
			return res;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Oil = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		Dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(Dist[i], Integer.MAX_VALUE); // 최단거리를 구해야 하므로 INF 초기화
		}

		st = new StringTokenizer(br.readLine());
		tY = Integer.parseInt(st.nextToken()) - 1; // 1~N
		tX = Integer.parseInt(st.nextToken()) - 1;

		pasngerStart = new ArrayList<>();
		pasngerDest = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			pasngerStart
					.add(new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, i });
			pasngerDest.add(new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 });
		}

		pq = new PriorityQueue<>();

		state = PASNGER_ON;
		int preState = PASNGER_ON;
		for (int i = 0; i < M * 2; i++) { // 승객을 찾고, 승객을 내리게 하고 = M * 2
			pq.offer(new Point(tY, tX, 0, Oil));
			Dist[tY][tX] = 0; // 출발지점은 최단거리 0
			preState = state;
			bfs();
			if (preState == state || Oil == -1) { 
				// 상태가 변하지 않았다? -> 승객을 찾지 못하거나 내리게 하지 못함
				// 운행 도중 연료가 바닥났다
				System.out.println(-1);
				return;
			}

			for (int j = 0; j < N; j++)
				Arrays.fill(Dist[j], Integer.MAX_VALUE); // Dist 초기화

			pq.clear(); // pq 비우기
		}

		System.out.println(Oil);
	}

	private static void bfs() {
		// state를 두고
		// 승객을 태워야 하는 상태) pasngerStart의 최단거리 찾음
		// 승객이 내려야 하는 상태) pasngerDest의 최단거리 찾음
		int addDistance = 1;
		while (!pq.isEmpty()) {
			int size = pq.size();

			while (size-- != 0) {
				Point polled = pq.poll();
				int y = polled.y;
				int x = polled.x;
				int dist = polled.dist;
				int oil = polled.oil;

				if (Dist[y][x] < dist)
					continue; // 이동하려는 곳보다 기록된 곳이 더 작다면 볼 필요없음

				if (state == PASNGER_ON) { // 승객을 태워야하는 상태
					for (Iterator iterator = pasngerStart.iterator(); iterator.hasNext();) {
						int[] is = (int[]) iterator.next();
						if (y == is[0] && x == is[1]) { // 승객 태움
							iterator.remove();
							state = PASNGER_OFF; // 승객을 내려줘야 하는 상태로 변경
							Oil = oil; // 남은 기름 양 업데이트
							tY = y; // 택시 출발 위치 업데이
							tX = x;
							dest = is[2];
							return;
						}
					}
				} else { // 택시에 탄 승객을 내려줘야 하는 상태
					int[] is = pasngerDest.get(dest); // 승객의 도착지
					if (y == is[0] && x == is[1]) { // 승객 내려줌
						state = PASNGER_ON; // 승객을 태워야 하는 상태로 변경

						Oil = oil + (addDistance - 1) * 2; // 그 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전된다
						tY = y; // 택시 출발 위치 업데이
						tX = x;
						return;
					}
				}

				if (oil == 0) { // 운행도중 연료가 바닥나면
					Oil = -1;
					return;
				}

				for (int d = 0; d < 4; d++) { // 상하좌우
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1)
						continue;

					if (Dist[ny][nx] > addDistance) {
						Dist[ny][nx] = addDistance;
						pq.offer(new Point(ny, nx, Dist[ny][nx], oil - 1));
					}
				}
			} // end of size
			addDistance++;
		} // end of pq
	} // end of bfs

}
