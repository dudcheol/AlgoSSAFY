package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12100_2048Easy {
	static int N;
	static int max;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] convert = { 1, 0, 3, 2 }; // 상하좌우 -> 하상우좌

	public static void main(String[] args) throws Exception {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		max = 0;
		dfs(0, map);
		System.out.println(max);
	}

	private static void dfs(int k, int[][] map) {
		// 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록 출력
		if (k == 5) {
			findMax(map); // map에 있는 가장 큰 숫자 찾아서 max 업데이트
			return;
		}

		dfs(k + 1, simulate(0, deepCopy(map)));
		dfs(k + 1, simulate(1, deepCopy(map)));
		dfs(k + 1, simulate(2, deepCopy(map)));
		dfs(k + 1, simulate(3, deepCopy(map)));
	}

	private static int[][] simulate(int d, int[][] map) {
		fillBlank(d, map);

		// d 방향을 기준으로 블럭 합치기
		// d 방향부터 시작해서 다음줄로 나아가면서 현재줄과 다음줄 비교
		// => 같으면 현재줄에 더한 값 넣고, 비교줄 0으로 바꿈
		// => 다르면 다음으로
		switch (d) {
		case 0: // 상
			for (int cur = 0; cur < N - 1; cur++) {
				int next = cur + 1;
				for (int i = 0; i < N; i++) {
					if (map[cur][i] != 0 && map[cur][i] == map[next][i]) {
						map[cur][i] *= 2;
						map[next][i] = 0;
					}
				}
			}
			break;
		case 1: // 하
			for (int cur = N - 1; cur >= 1; cur--) {
				int next = cur - 1;
				for (int i = 0; i < N; i++) {
					if (map[cur][i] != 0 && map[cur][i] == map[next][i]) {
						map[cur][i] *= 2;
						map[next][i] = 0;
					}
				}
			}
			break;
		case 2: // 좌
			for (int cur = 0; cur < N - 1; cur++) {
				int next = cur + 1;
				for (int i = 0; i < N; i++) {
					if (map[i][cur] != 0 && map[i][cur] == map[i][next]) {
						map[i][cur] *= 2;
						map[i][next] = 0;
					}
				}
			}
			break;
		case 3: // 우
			for (int cur = N - 1; cur >= 1; cur--) {
				int next = cur - 1;
				for (int i = 0; i < N; i++) {
					if (map[i][cur] != 0 && map[i][cur] == map[i][next]) {
						map[i][cur] *= 2;
						map[i][next] = 0;
					}
				}
			}
			break;
		}

		// 블럭합치는 작업을 해줬으므로 한 번 더 빈칸 채우기
		return fillBlank(d, map);
	}

	private static int[][] fillBlank(int d, int[][] map) {
		// d 방향을 기준으로 빈 칸 메꾸기
		// d 방향부터 시작해서 0인 부분을 찾음
		// => 0이면 빈칸이므로 d 방향으로 계속 이동해보면서 0이아닌 블럭과 스왑함
		// => 0이 아니면 다음으로
		// 상하좌우별로 시작지점이 달라짐
		switch (d) {
		case 0:
		case 2:
			d = convert[d];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						int ny = i;
						int nx = j;
						while (true) {
							ny += dy[d];
							nx += dx[d];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								break;
							if (map[ny][nx] == 0)
								continue;
							map[i][j] = map[ny][nx];
							map[ny][nx] = 0;
							break;
						}
					}
				}
			}
			break;
		case 1:
		case 3:
			d = convert[d];
			for (int i = N - 1; i >= 0; i--) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] == 0) {
						int ny = i;
						int nx = j;
						while (true) {
							ny += dy[d];
							nx += dx[d];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								break;
							if (map[ny][nx] == 0)
								continue;
							map[i][j] = map[ny][nx];
							map[ny][nx] = 0;
							break;
						}
					}
				}
			}
			break;
		}
		return map;
	}

	private static void findMax(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
	}

	// clone보다 System.arraycopy가 더 빠름
	private static int[][] deepCopy(int[][] origin) {
		int[][] ret = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(origin[i], 0, ret[i], 0, N);
		}
		return ret;
	}

	private static void printMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------");
	}
}
