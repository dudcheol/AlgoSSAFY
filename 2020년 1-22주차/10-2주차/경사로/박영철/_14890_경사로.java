package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14890_경사로 {
	static int N, L;
	static int[][] map;
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(simulate());
	}

	private static int simulate() {
		// 모든 경우의 수 확인하기
		int answer = 0;
		// 모든 행 돌면서 가로방향 확인해보기
		loop: for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				int dif = Math.abs(map[i][j - 1] - map[i][j]); // 이전 칸과의 높이 차이
				if (dif == 1) { // 차이가 1나면 경사로 놓기를 시도해본다
					if (map[i][j - 1] > map[i][j]) { // 왼쪽이 더 높다면 현재칸부터 L-1칸 오른쪽이 같아야함
						for (int l = 0; l < L; l++) {// 이 칸에서부터 L만큼 같은 숫자가 나와야 경사로 놓을 수 있음
							int nj = j + l;
							if (nj >= N || map[i][j] != map[i][nj] || visited[i][nj] != 0)
								continue loop; // 범위를 벗어남, 높이가 다르거나, 이미 지어진 경사로가 있다면 경사로 지을 수 없음
						}
						for (int l = 0; l < L; l++) // 경사로 지을 수 있으면 visited에 표시하기
							visited[i][j + l] = map[i][j];

					} else { // 오른쪽이 더 높다면 이전칸부터 L-1칸 왼쪽이 같아야함
						for (int l = 0; l < L; l++) {
							int nj = j - 1 - l;
							if (nj < 0 || map[i][j - 1] != map[i][nj] || visited[i][nj] != 0)
								continue loop;
						}
						for (int l = 0; l < L; l++)
							visited[i][j - 1 - l] = map[i][j - 1];
					}
				} else if (dif >= 2) // 차이가 2이상 나면 경사로로 해결할 수 없음
					continue loop;
			}
			answer++; // 반복문에서 continue없이 여기까지 왔다면 "지나갈 수 있는 길"임
		}

		visited = new int[N][N]; // 놓았던 경사로 초기화

		// 모든 열 돌면서 세로방향 확인해보기
		loop: for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				int dif = Math.abs(map[j - 1][i] - map[j][i]); // 이전 칸과의 높이 차이
				if (dif == 1) { // 차이가 1나면 경사로 놓기를 시도해본다
					if (map[j - 1][i] > map[j][i]) { // 위쪽이 더 높다면 현재칸부터 L-1칸 아래쪽이 같아야함
						for (int l = 0; l < L; l++) {// 이 칸에서부터 L만큼 같은 숫자가 나와야 경사로 놓을 수 있음
							int nj = j + l;
							if (nj >= N || map[j][i] != map[nj][i] || visited[nj][i] != 0)
								continue loop; // 범위를 벗어남, 높이가 다르거나, 이미 지어진 경사로가 있다면 경사로 지을 수 없음
						}
						for (int l = 0; l < L; l++) // 경사로 지을 수 있으면 visited에 표시하기
							visited[j + l][i] = map[j][i];

					} else { // 아래쪽이 더 높다면 이전칸부터 L-1칸 위쪽이 같아야함
						for (int l = 0; l < L; l++) {
							int nj = j - 1 - l;
							if (nj < 0 || map[j - 1][i] != map[nj][i] || visited[nj][i] != 0)
								continue loop;
						}
						for (int l = 0; l < L; l++) // 경사로 지을 수 있으면 visited에 표시하기
							visited[j - 1 - l][i] = map[j - 1][i];
					}
				} else if (dif >= 2) // 차이가 2이상 나면 경사로로 해결할 수 없음
					continue loop;
			}
			answer++;
		}

		return answer;
	}
}
