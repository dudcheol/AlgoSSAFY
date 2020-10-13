package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14499_주사위굴리기 {

	private static int y;
	private static int x;
	private static int M;
	private static int N;
	private static int[][] map;
	private static int K;
	private static int[] dice;
	private static int[] dx = { 0, 0, 0, -1, 1 }; // 동서북남 - 우좌상하
	private static int[] dy = { 0, 1, -1, 0, 0 }; // 동서북남 - 우좌상하

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 주사위 만들기
		dice = new int[7];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			if (simulate(Integer.parseInt(st.nextToken()))) {
				sb.append(dice[1]).append('\n');
			}
		}

		System.out.print(sb);
	}

	private static boolean simulate(int cmd) {
		// 주사위 이동
		int nx = x + dx[cmd];
		int ny = y + dy[cmd];
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;

		// 주사위 움직임
		int tmp = -1;
		switch (cmd) {
		case 1: // 동
			tmp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
			break;
		case 2: // 서
			tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 3: // 북
			tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		case 4: // 남
			tmp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		}

		// 주사위가 움직인 자리에 뭐가 있는지에 따라 처리
		x = nx;
		y = ny;
		if (map[x][y] == 0) {
			map[x][y] = dice[6];
		} else {
			dice[6] = map[x][y];
			map[x][y] = 0;
		}

		return true;
	}

}
