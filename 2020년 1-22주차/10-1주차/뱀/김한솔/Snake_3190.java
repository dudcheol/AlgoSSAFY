/**
 * Backjoon - 3190. 뱀
 * Snake_3190.java
 * @date 2020-10-06
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Dir {
	int time;
	char dir;

	public Dir(int time, char dir) {
		this.time = time;
		this.dir = dir;
	}
}

public class Snake_3190 {

	static int N, K, L;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 맵의 크기
		map = new int[N + 1][N + 1]; // map생성

		K = Integer.parseInt(br.readLine()); // 사과의 개수

		// 사과 위치정보 저장
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1; // 사과가 있음을 표기
		}

		L = Integer.parseInt(br.readLine()); // 방향 정보 개수

		ArrayList<Dir> dirs = new ArrayList<>();

		// 방향정보 dirs에 저장
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			dirs.add(new Dir(t, dir));
		}

		Deque<int[]> snake = new LinkedList<>();
		snake.addLast(new int[] { 1, 1 }); // 뱀의 위치 넣기
		map[1][1] = 2; // 뱀 첫 위치 표기

		int snakeDir = 0; // 좌측방향
		int dirsIdx = 0; // 방향정보 인덱스
		int sec = 0;

		int dx[] = { 0, 1, 0, -1 }; // 우, 하, 좌, 상
		int dy[] = { 1, 0, -1, 0 };

		while (true) {
			
			// 0. 시간 증가
			sec++;

			// 2. 현재 방향으로 한 칸 앞 확인
			int[] cur = snake.getLast();
			int nx = cur[0] + dx[snakeDir];
			int ny = cur[1] + dy[snakeDir];

			// 2-1. 이동할 수 없는 범위(0이하 또는 N이상)인 경우거나 자신의 몸과 부딪힌 경우 현재 시간 반환
			if (nx <= 0 || nx > N || ny <= 0 || ny > N || map[nx][ny] == 2) {
				break;
			}

			// 4. 이동할 수 있는 경우 한 칸앞의 값이 사과인지 확인
			if (map[nx][ny] != 1) { // 4-1. 사과인 경우 ---> 사과표시 제거
				int[] delete = snake.pollFirst();
				map[delete[0]][delete[1]] = 0;
			} // 4-2. 사과가 아닌 경우 ---> snake큐에서 맨 앞 위치정보 제거

			map[nx][ny] = 2;
			snake.addLast(new int[] { nx, ny });

			// 1. 방향정보를 가져와 방향을 전환해야 하는 지 확인
			// 1-1. 방향이 바뀌는 경우
			if (dirsIdx < dirs.size() && sec == dirs.get(dirsIdx).time) {

				if (dirs.get(dirsIdx).dir == 'D') { // 오른쪽으로 90도 회전
					snakeDir = (snakeDir + 1) % 4;
				} else { // 왼쪽으로 90도 회전
					snakeDir--;
					snakeDir = snakeDir < 0 ? 3 : snakeDir;
				}

				dirsIdx++;
			}
		}
		System.out.println(sec);
	}

}
