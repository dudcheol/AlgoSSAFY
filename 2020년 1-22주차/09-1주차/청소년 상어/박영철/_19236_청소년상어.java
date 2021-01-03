package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _19236_청소년상어 {

	static Fish[][] map;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 반시계
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 반시계
	static int max;

	static class Fish {
		int num;
		int dir;

		public Fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new Fish[4][4];

		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				map[r][c] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
			}
		}

		max = 0;
		int n = map[0][0].num;
		map[0][0].num = 0;
		// 물고기이동, 상어이동 시뮬레이션 시작
		simulate(0, 0, map[0][0].dir, n);

		// 상어가 먹을 수 있는 물고기 번호의 합의 최댓값은?
		System.out.println(max);
	}

	private static void simulate(int sy, int sx, int dir, int sum) {
//		System.out.println("shark move");
//		System.out.println("eat : " + sum);
		max = Math.max(max, sum);
//		map[sy][sx].num = 0;
//		print();
		// 물고기 이동
		moveFish(sy, sx);
//		System.out.println("fish move");
//		print();

		// 물고기 이동끝나면 상어가 이동
		int ny = sy;
		int nx = sx;
		while (true) {// 한번에 여러 칸 이동 가능
			ny += dy[dir];
			nx += dx[dir];
			if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				break;
			if (map[ny][nx].num == 0)// 물고기가 없는 칸으로 이동 불가
				continue;
			// 물고기 있는 칸으로 이동-> 그 칸에 있는 물고기 먹음, 그 물고기가 가졌던 방향을 가짐
			Fish[][] tmp = copyMap();
			int n = map[ny][nx].num;
			map[ny][nx].num = 0; // 물고기 먹음
			simulate(ny, nx, map[ny][nx].dir, sum + n);// 상어가 이동한 후 다시 물고기 이동하고 과정 반복
			map = tmp;
		}
	}

	private static Fish[][] copyMap() {
		Fish[][] ret = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Fish m = map[i][j];
				ret[i][j] = new Fish(m.num, m.dir);
			}
		}
		return ret;
	}

	private static void print() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				String tmp = "(" + map[i][j].num + "," + map[i][j].dir + ") ";
				System.out.print(String.format("%8s", tmp));
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}

	private static void moveFish(int sy, int sx) {
		// 물고기 한 칸 이동
		// 이동할 수 없는 칸 : 상어가 있는 칸, 경계를 넘어가는 칸
		// 물고기는이동할 수 있는 칸을 찾을 때 까지 45도 반시계 회전하며 찾음 -> 없으면 이동하지 않음
		// 물고기가 다른 물고기가 있는 칸으로 이동 -> 서로의 위치를 바꿈

		int target = 1;
		while (target <= 16) {
			loop: for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (target == map[i][j].num) { // target 물고기 찾음
						int dir = map[i][j].dir;
						for (int k = 0; k < 8; k++) {
							int ny = i + dy[dir];
							int nx = j + dx[dir];
							if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || (ny == sy && nx == sx)) {
								// 맵을 벗어나거나, 상어의 위치이면 이동할 수 없음 -> 방향을 바꾸고 다시 시도
								dir = (dir + 1) % 8;
								continue;
							}
							int tmpnum = map[i][j].num;
							int tmpdir = dir; // !!!! 오답원인 !!!! : 변경된 물고기 방향을 적용해주지 않았음
							map[i][j].num = map[ny][nx].num;
							map[i][j].dir = map[ny][nx].dir;
							map[ny][nx].num = tmpnum;
							map[ny][nx].dir = tmpdir;
							break loop;
						}
					}
				}
			}
//			System.out.println("move fish : " + target);
//			print();
			target++;
		}
	}

}
