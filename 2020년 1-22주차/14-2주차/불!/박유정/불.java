package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];

		Queue<int[]> fire = new LinkedList<int[]>();//불위치 담을 배열
		Queue<int[]> jh = new LinkedList<int[]>();//지훈이 위치 담을 배열

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'J') {//지훈이 위치
					jh.add(new int[] { i, j });
					map[i][j] = '.';
				}
				if (map[i][j] == 'F') {//불위치
					fire.add(new int[] { i, j });
				}
			}
		}
		
		boolean visit[][] =new boolean[R][C];//지훈이 위치 visit 체크
		int answer = 0;
		
		while (true) {
			int size = fire.size();
			for (int i = 0; i < size; i++) {//한칸씩만 이동
				int[] arr = fire.poll();

				int m = arr[0];
				int n = arr[1];

				for (int j = 0; j < 4; j++) {
					int dx = m + d[j][0];
					int dy = n + d[j][1];

					if (dx < 0 || dy < 0 || dx >= R || dy >= C || map[dx][dy] == '#' || map[dx][dy] == 'F')
						continue;

					map[dx][dy] = 'F';
					fire.add(new int[] { dx, dy });
				}
			}
			answer++;
			int size2 = jh.size();
			boolean flag = false;
			
			for (int i = 0; i < size2; i++) {//한씩만이동
				int[] arr = jh.poll();
				int m = arr[0];
				int n = arr[1];
				for (int j = 0; j < 4; j++) {
					int dx = m + d[j][0];
					int dy = n + d[j][1];

					if (dx < 0 || dy < 0 || dx >= R || dy >= C) {//탈출했음
						System.out.println(answer);
						return;
					}
					if (map[dx][dy] == '#' || map[dx][dy] == 'F'||visit[dx][dy])
						continue;
					
					visit[dx][dy]=true;
					flag = true;
					jh.add(new int[] { dx, dy });
				}
			}
			
			if (!flag) {//지훈이 이동못했으면
				System.out.println("IMPOSSIBLE");
				return;
			}
		}
	}

}
