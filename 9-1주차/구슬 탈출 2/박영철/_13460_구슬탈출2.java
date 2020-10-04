package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13460_구슬탈출2 {
	static int N, M;
	static char[][] map;
	static int min;
	static int[] goal;
	static int[] dy = { 0, 0, -1, 1 }; // 좌,우,상,하
	static int[] dx = { -1, 1, 0, 0 }; // 좌,우,상,하

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'O')
					goal = new int[] { i, j };
			}
		}

		dfs(0);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void dfs(int cnt) {
		if (cnt > 10)
			return;

		if (min != Integer.MAX_VALUE && min < cnt)
			return;

		// map 검사
		int red = 0;
		int[] redPos = new int[2];
		int blue = 0;
		int[] bluePos = new int[2];
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j] == 'R') {
					red++;
					redPos[0] = i;
					redPos[1] = j;
				} else if (map[i][j] == 'B') {
					blue++;
					bluePos[0] = i;
					bluePos[1] = j;
				}
			}
		}

		if (blue == 0) // 파랑이 구멍에 들어가면 실패
			return;
		else if (red == 0) { // 빨강만 구멍에 들어갔다면 성공
			min = Math.min(min, cnt);
			return;
		}
		// 빨, 파 둘 다 있다면 계속 진행
		
		// 기울이기 시뮬레이션 진행
//		char[][] tmp = deepCopy(map); << 얘를 for문 밖에 해놓으면, map = tmp가 있는 줄에서 "얕은 복사"가 되버림
		for (int d = 0; d < 4; d++) {
//			printMap();
			char[][] tmp = deepCopy(map); /* 실수 : 경우마다 tmp 배열이 있어야 함 */
			if (simulate(d, redPos, bluePos)) 
				dfs(cnt + 1);
			map = tmp;
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}

	private static boolean simulate(int dir, int[] rp, int[] bp) {
		// dir 방향으로 기울이기
		int ry = rp[0];
		int rx = rp[1];
		int prery = ry;
		int prerx = rx;
		int by = bp[0];
		int bx = bp[1];
		int preby = by;
		int prebx = bx;

		boolean goalRed = false;
		while (true) { // 빨,파 동시에 움직여야함

			if (!goalRed) {
				ry = ry + dy[dir];
				rx = rx + dx[dir];
				if (map[ry][rx] == '#' || map[ry][rx] == 'B') {
					ry = prery; // 공을 이동시키지 않음
					rx = prerx;
				} else {
					map[prery][prerx] = '.';
					if (map[ry][rx] != 'O')
						map[ry][rx] = 'R';
					else { // 빨간공이 구멍에 들어갔다면
						goalRed = true;
					}
				}
			}

			by = by + dy[dir];
			bx = bx + dx[dir];
			if (map[by][bx] == '#' || map[by][bx] == 'R') {
				by = preby;
				bx = prebx;
			} else {
				map[preby][prebx] = '.';
				if (map[by][bx] != 'O')
					map[by][bx] = 'B';
				else { // 파랑공이 구멍에 들어갔다면 해당 방향으로 이동하면 안됨
					return false;
				}
			}

			if (ry == prery && rx == prerx && by == preby && bx == prebx) { // 두 공이 이동하지 않았다면 종료
				if (rp[0] == ry && rp[1] == rx && bp[0] == by && bp[1] == bx)
					return false;// 공이 한 번도 움직이지 않았다면 다른 방향으로 기울기 위해 false 리턴
				else {
					return true;
				}
			}

			prery = ry;
			prerx = rx;
			preby = by;
			prebx = bx;
		}
	}

	private static char[][] deepCopy(char[][] origin) {
		char[][] ret = new char[N][M];
		for (int i = 0; i < N; i++)
			ret[i] = origin[i].clone();
		return ret;
	}

}
