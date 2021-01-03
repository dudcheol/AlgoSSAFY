package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/* 2시간 35분 */
public class _19237_어른상어 {
	static int N, M, K;
	static ArrayList<Shark> list;
	static Point[][] map;
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };
	// 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽을 의미

	static class Point {
		int shark;
		int k;

		public Point(int shark, int k) {
			this.shark = shark;
			this.k = k;
		}
	}

	static class Shark {
		int num;
		int y;
		int x;
		int dir;
		int[][] dirs;

		public Shark(int num, int y, int x) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.dirs = new int[5][];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // (2 ≤ N ≤ 20, 2 ≤ M ≤ N2, 1 ≤ k ≤ 1,000)
		M = Integer.parseInt(st.nextToken()); // 상어 1~M 번호 붙여 있음
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		map = new Point[N][N];// NxN map
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur == 0) {
					map[i][j] = new Point(0, 0);
				} else {
					list.add(new Shark(cur, i, j));
					map[i][j] = new Point(cur, K);// 상어는 자신의 위치에 냄새 뿌림
				}
			}
		}

		list.sort((o1, o2) -> {
			return Integer.compare(o1.num, o2.num);
		});

		// 상어의 처음 방향
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list.get(i).dir = Integer.parseInt(st.nextToken());
		}

		// 각 상어의 우선순위
		for (int i = 0; i < M; i++) {// M개 칸에 상어 한마리씩 존재
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				list.get(i).dirs[j] = new int[] { 0, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			}
		}

		System.out.println(simulate());// 1번 상어만 격자에 남게 되기까지 몇 초가 걸리는지를 구하는 프로그램

		// 상어1 가장 강력해서 모두 쫓아냄

		// k번 이동 시 냄새 사라짐

	}

	private static int simulate() {
		int timer = 0;

		while (list.size() > 1) {
//			printMap();
			if(timer >= 1000) return -1;

			// 매 초마다 tmp 맵이 필요함
			Point[][] tmp = deepCopy(map);

			int size = list.size();
			ArrayList<Integer> removeShark = new ArrayList<>();
			loop: for (int i = 0; i < size; i++) {
				Shark shark = list.get(i);
				int sharkNum = shark.num;
				int y = shark.y;
				int x = shark.x;
				int dir = shark.dir;
				int[][] dirs = shark.dirs;

				// 1초 후 상하좌우 하나로 이동
				int my = -1;
				int mx = -1;
				int md = -1;
				boolean find = false;

				for (int d = 1; d <= 4; d++) {
					int ny = y + dy[dirs[dir][d]];
					int nx = x + dx[dirs[dir][d]];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N) // 맵 밖으로 벗어나면 컨티뉴
						continue;

					// 상어 이동 방향 결정 시, 먼저 인접한 칸 중 아무 냄새 없는 칸 이동
					if (map[ny][nx].k == 0) { // 아무 냄새 없는 칸이면 바로 이동
						// 가능한 칸 여러개 일 시,특정한 우선순위 존재. 상어마다 다름. 상어가 보는 방향에 따라 다름.
						if (tmp[ny][nx].k != 0) { // 한 칸에 여러마리 있으면 가장 작은 번호만남음
							if (tmp[ny][nx].shark > sharkNum) {
								removeShark.add(tmp[ny][nx].shark);// 원래 tmp 에 있던 상어 쫓겨남
								shark.dir = dirs[dir][d]; // 이동한 방향이 보고있는 방향이 됨
								shark.y = ny; // 상어 위치 이동
								shark.x = nx;
								tmp[ny][nx].shark = sharkNum;
								tmp[ny][nx].k = K + 1;
							} else {
								removeShark.add(sharkNum);// 지금 상어 쫓겨남
							}
						} else { // 비어있다면 이동시킴
							shark.dir = dirs[dir][d]; // 이동한 방향이 보고있는 방향이 됨
							shark.y = ny; // 상어 위치 이동
							shark.x = nx;
							tmp[ny][nx].shark = sharkNum;
							tmp[ny][nx].k = K + 1;
						}
						continue loop;
					} else if (!find && map[ny][nx].shark == sharkNum) {
						my = ny;
						mx = nx;
						md = d;
						find = true; // 가장 처음 발견된 나의 냄새가 있던 곳을 기록하기 위한 플래그
					}
				}

				// 아무 냄새 없는 칸이 없었다면 자신의 냄새가 있던 칸으로 이동
				shark.dir = dirs[dir][md];
				shark.y = my;
				shark.x = mx;
				tmp[my][mx].k = K + 1;
			} // end of for

			// map 업데이트
			map = tmp;

			// map의 시간 k값 모두 1씩 내림
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].k > 0)
						map[i][j].k--;
					else
						map[i][j].k = 0;
				}
			}

			// 쫓겨난 상어 제거
			for (int i = 0; i < removeShark.size(); i++) {
				int cur = removeShark.get(i);

				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Shark shark = (Shark) iterator.next();
					if (shark.num == cur) {
						iterator.remove();
						continue;
					}
				}
			}

			timer++;
		}

		return timer;
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print("[" + map[i][j].shark + "," + map[i][j].k + "]");
			}
			System.out.println();
		}
		System.out.println("---------------------");
	}

	private static Point[][] deepCopy(Point[][] origin) {
		Point[][] ret = new Point[N][N];
		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < origin[i].length; j++) {
				ret[i][j] = new Point(origin[i][j].shark, origin[i][j].k);
			}
		}
		return ret;
	}

}
