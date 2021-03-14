package test;
//답 참고 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미생물격리 {
	static int[][] d = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Micro> q = new LinkedList<>();

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][][] visit = new int[N][N][3];// 미생물 총값, 미생물 max값,방향

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int h = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				q.add(new Micro(h, w, n, dir));
			}
			for (int i = 0; i < M; i++) {
				while (!q.isEmpty()) {
					Micro m = q.poll();
					int x = m.x;
					int y = m.y;
					int num = m.num;
					int dir = m.dir;
					// 이동
					int dx = x + d[dir][0];
					int dy = y + d[dir][1];
					// 군집 합치고 자르고(미생물계산)
					if (dx == 0 || dx == N - 1 || dy == 0 || dy == N - 1) {
						num /= 2;
						dir += (dir % 2 == 0) ? -1 : 1;
					}
					if (visit[dx][dy][1] < num) {
						visit[dx][dy][1] = num;
						visit[dx][dy][2] = dir;
					}
					visit[dx][dy][0] += num;
				}
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < N; m++) {
						if (visit[n][m][0] != 0) {
							q.add(new Micro ( n, m, visit[n][m][0], visit[n][m][2] ));
							visit[n][m][0] = 0;
							visit[n][m][1] = 0;
						}
					}
				}

			}
			int sum = 0;
			while (!q.isEmpty()) {
				sum += q.poll().num;
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);

	}

}

class Micro implements Comparable<Micro> {
	int x;
	int y;
	int num;
	int dir;

	public Micro(int x, int y, int num, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
		this.dir = dir;
	}

	@Override
	public int compareTo(Micro o) {
		if (this.x == o.x) {
			if (this.y == o.y) {
				return o.num - this.num;
			}
			return this.y - o.y;
		}
		return this.x - o.x;
	}
}
