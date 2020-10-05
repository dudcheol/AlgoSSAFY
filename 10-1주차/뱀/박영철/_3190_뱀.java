package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _3190_뱀 {
	static final int APPLE = 3;
	static int N, K, L;
	static int[][] map;
	static int[] times;
	static int[] dirs;
	static int[] dy = { 0, 1, 0, -1 }; // 우,하,좌,상
	static int[] dx = { 1, 0, -1, 0 }; // 우,하,좌,상
	static LinkedList<Point> snake;
	static int headDir;

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object obj) {
			Point objs = (Point) obj;
			return this.y == objs.y && this.x == objs.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 보드의 크기 NxN
		map = new int[N][N];
		K = Integer.parseInt(br.readLine()); // 사과위치
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
		}
		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		times = new int[L];
		dirs = new int[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			times[i] = Integer.parseInt(st.nextToken());
			dirs[i] = st.nextToken().charAt(0) == 'D' ? 1 : 3;
		}

		snake = new LinkedList<>();
		snake.offer(new Point(0, 0));// 뱀은 0,0에서 시작
		headDir = 0; // 오른쪽을 향함

		// 시뮬레이션 시작!
		System.out.println(simulate() + 1);
	}

	private static int simulate() {
		int timer = 0;
		int order = 0;
		while (true) {
			// 뱀 머리 방향으로 이동
			Point head = snake.peek();
			int y = head.y;
			int x = head.x;
			// 머리를 이동방향으로 이동
			int ny = y + dy[headDir];
			int nx = x + dx[headDir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) { // 벽에 부딪힘
				return timer;
			}
			Point newHead = new Point(ny, nx);
			if (snake.contains(newHead)) {// 자기자신의 몸과 부딪힘
				return timer;
			}
			
			snake.offerFirst(newHead);// 머리를 다음칸에 위치시킴
			if (map[ny][nx] == APPLE) {// 이동방향에 사과가 있다면
				map[ny][nx] = 0; // 꼬리 움직이지 않고, 사과는 없어짐
			} else {// 사과가 없다면
				snake.pollLast();// 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌
			}

			timer++;
			if (order < L && times[order] == timer) {
				headDir = (headDir + dirs[order]) % 4;
				order++;
			}
		}
	}

}
