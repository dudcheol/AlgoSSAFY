/**
 * Backjoon - 8972. 미친 아두이노
 * CrazyArduino_8972.java
 * @date 2020-11-29
 * @author hansolKim
 **/

package p8000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CrazyArduino_8972 {

	static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static int R, C, jX, jY, CRAZY_SIZE;
	static char[][] map;
	static Queue<int[]> crazyArduinos;
	static int[] moves;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		crazyArduinos = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') { // 미친 아두이노인 경우
					crazyArduinos.add(new int[] { i, j });
				}
				if (map[i][j] == 'I') { // 미친 아두이노인 경우
					jX = i;
					jY = j;
					map[i][j] = '.';
				}
			}
		}

		CRAZY_SIZE = crazyArduinos.size();

		String move = br.readLine();

		moves = new int[move.length()];
		for (int i = 0; i < move.length(); i++) {
			moves[i] = move.charAt(i) - '0';
		}

		game();

	}

	private static void game() {

		for (int i = 0; i < moves.length; i++) {

			// 1. 종수위치 변경 구현
			int move = moves[i]; // 종수의 움직임

			jX += dx[move];
			jY += dy[move];
			
			// 변경된 위치에 미친 아두이노가 있는 경우 --> 종료
			if (map[jX][jY] == 'R') {
				System.out.println("kraj " + (i + 1));
				return;
			}

			// 2. 미친 아두이노들의 움직임 구현
			for (int j = 0; j < CRAZY_SIZE; j++) {
				int[] arduinoInfo = crazyArduinos.poll();
				int cx = arduinoInfo[0];
				int cy = arduinoInfo[1];
				
				int[] dist = new int[10];
				for (int k = 1; k <= 9; k++) {
					dist[k] = 200;
				}

				for (int k = 1; k <= 9; k++) {
					int nx = cx + dx[k];
					int ny = cy + dy[k];

					// 영역 밖인 경우
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
						continue;
					}

					dist[k] = getDist(jX, jY, nx, ny);
				}
				
				// 가장 거리가 짧은 방향 선택
				int minDist = 200;
				int dir = 1;
				for (int k = 1; k <= 9; k++) {
					if (dist[k] < minDist) {
						dir = k;
						minDist = dist[k];
					}
				}

				int nx = cx + dx[dir];
				int ny = cy + dy[dir];

				if (nx == jX && ny == jY) { // 미친 아두이노가 종수를 잡은 경우
					System.out.println("kraj " + (i + 1));
					return;
				}

				if (map[cx][cy] == 'R') { // 원래 있던 칸 --> 빈칸처리
					map[cx][cy] = '.';
				}

				if (map[nx][ny] == 'A' || map[nx][ny] == '#') { // 이미 존재한 경우
					map[nx][ny] = '#'; // 중복처리
				} else {
					map[nx][ny] = 'A'; // 변경된 위치 표기
				}
				crazyArduinos.add(new int[] { nx, ny });

			}

			// 3. 겹치는 아두이도 있는 지 확인
			int size = CRAZY_SIZE;
			for (int j = 0; j < size; j++) {
				int[] nInfo = crazyArduinos.poll();
				int nx = nInfo[0];
				int ny = nInfo[1];

				if (map[nx][ny] != '#') { // 중복된 곳이 아니면
					map[nx][ny] = 'R';
					crazyArduinos.add(new int[] { nx, ny });
				} else {
					CRAZY_SIZE--;
				}
			}

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '#') {
						map[r][c] = '.';
					}
				}
			}
		}

		map[jX][jY] = 'I';
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}

	private static int getDist(int x, int y, int tx, int ty) {
		return Math.abs(x - tx) + Math.abs(y - ty);
	}

}
