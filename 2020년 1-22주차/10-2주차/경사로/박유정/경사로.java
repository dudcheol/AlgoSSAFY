import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
	static int N, L, count;
	static int[][] map;
	static int[][] d = { { 0, 1 }, { 1, 0 } };//오른쪽, 아래 dir로 조정

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			boolean[] check1 = new boolean[N];//경사로가 있는지 체크해주는 배열
			boolean[] check2 = new boolean[N];
			dfs(i, 0, map[i][0], 0, check1);//아래방향으로 길 만들기
			dfs(0, i, map[0][i], 1, check2);//오른쪽 방향으로 길만들기
		}
		System.out.println(count);
	}

	private static void dfs(int x, int y, int prev, int dir, boolean[] check) {
		//System.out.println(x + "," + y + "," + count);
		if ((x == N - 1 && dir == 1) || (y == N - 1 && dir == 0)) {// 길끝에 도달
			count++;
			return;
		}

		int nx = x + d[dir][0];
		int ny = y + d[dir][1];

		if (map[nx][ny] == prev) // 전 높이와 같은경우
			dfs(nx, ny, prev, dir, check);

		else if (map[nx][ny] == prev - 1) {// 전 높이보다 한칸 낮을때->앞쪽에 경사로를 세워야함
			boolean flag = true;
			int m = nx, n = ny;

			if (dir==1)
				check[m] = true;//아래방향 행의 좌표 사용 경사로 설치
			else {
				check[n] = true;//오른쪽 방향 열의 좌표 사용
			}

			for (int j = 1; j < L; j++) {// L-1만큼 반복되는지 체크
				m += d[dir][0];
				n += d[dir][1];

				if (m >= N || n >= N || map[m][n] != map[nx][ny]) {// L만큼 반복되지 않으면 FALSE;
					flag = false;
					break;//만들수없는길
				}
				if (dir == 1)// 경사로 설치
					check[m] = true;
				else {
					check[n] = true;
				}
			}
			if (flag)// 만들 수 있는 길이면 계속 만듦
				dfs(m, n, map[nx][ny], dir, check);
			
		} else if (map[nx][ny] == prev + 1) {//전 높이보다 한칸 높을때->뒤쪽에 경사로를 세워야함
			boolean flag = true;
			int m = nx, n = ny;
		
			for (int j = 1; j <= L; j++) {// L만큼 반복되는지 체크
				m -= d[dir][0];
				n -= d[dir][1];

				if (m < 0 || n < 0 || map[m][n] != map[nx][ny]-1||(dir == 0 && check[n]) || (dir == 1 && check[m])) {// L만큼 반복되지 않으면 거나 경사로가 설치되어있으면 false
						flag = false;
						break;
				}
				if (dir == 1)
					check[m] = true;
				else {
					check[n] = true;
				}
			}
			if (flag)// 만들 수 있는 길이면 계속 만듦
				dfs(nx, ny, map[nx][ny], dir, check);
		}

	}

}
