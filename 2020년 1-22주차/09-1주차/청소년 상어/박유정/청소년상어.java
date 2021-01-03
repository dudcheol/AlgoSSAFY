package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 청소년상어 {

	static int[][] dx = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };// 1~8방향
	static final int DEAD = 100;
	static final int SHARK = -100;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int[][] map = new int[5][5];// 4x4
		int[][] point = new int[17][3];// 16물고기의 좌표,방향

		for (int i = 1; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				point[map[i][j]][0] = i;
				point[map[i][j]][1] = j;
				point[map[i][j]][2] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		shark(1, 1, map[1][1], map, point);
		System.out.println(answer);

	}

	public static void shark(int x, int y, int sum, int[][] map, int[][] point) {
		// 상어

		boolean flag = false;

		int[][] mapc = new int[5][5];
		int[][] pointc = new int[17][3];

		for (int i = 0; i < 5; i++) {
			mapc[i] = map[i].clone();
		}
		for (int i = 1; i < 17; i++) {
			pointc[i] = point[i].clone();
		}

		int a = mapc[x][y];
		int d = pointc[a][2];

		pointc[mapc[x][y]][0] = DEAD;// 죽었음 표시
		mapc[x][y] = SHARK;
		
		fish(mapc, pointc);// 물고기 움직임


		for (int i = 1; i < 4; i++) {// 한칸 두칸 세칸 움직임
			int mx = x + i * dx[d][0];
			int my = y + i * dx[d][1];

			if (mx < 1 || my < 1 || mx >= 5 || my >= 5)// 배열범위
				continue;
			if (mapc[mx][my] == DEAD)//빈칸이면
				continue;

			int num = mapc[mx][my];
			mapc[x][y] = DEAD;//전에 좌표는 빈공간 표시
			shark(mx, my, sum + num, mapc, pointc);
			flag = true;
		}

		if (!flag) {
			answer = Math.max(answer, sum);
		}
	}

	public static void fish(int[][] map, int[][] point) {
		// 물고기 이동
		//빈공간하고 교체가능
		for (int i = 1; i < 17; i++) {
			if (point[i][0] == DEAD)
				continue;
			int x = point[i][0];
			int y = point[i][1];
			int d = point[i][2];

			for (int j = 0; j < 8; j++) {
				int k = (d + j) % 8;
				int mx = x + dx[k][0];
				int my = y + dx[k][1];

				if (mx < 1 || my < 1 || mx >= 5 || my >= 5 || map[mx][my] == SHARK)
					continue;
				point[i][2] = k;
				change(x, y, mx, my, map, point);
				break;
			}
		}
	}

	public static void change(int x, int y, int mx, int my, int[][] map, int[][] point) {
		int a = map[x][y];
		int b = map[mx][my];

		map[x][y] = b;
		map[mx][my] = a;
		
		if (b == DEAD) {//빈공간일경우
			point[a][0] = mx;
			point[a][1] = my;
			return;
		}

		int ax = point[a][0];
		int ay = point[a][1];

		point[a][0] = point[b][0];
		point[a][1] = point[b][1];

		point[b][0] = ax;
		point[b][1] = ay;
	}
}
