import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		char[][] map = new char[N][M];
		Queue<Node> qu = new LinkedList<Node>();
		Queue<Node> fire = new LinkedList<Node>();

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'J') {
					qu.add(new Node(i, j));

				}
				if (map[i][j] == 'F') {
					fire.add(new Node(i, j));
				}
			}
		}

		int time = 0;
		while (true) {
			if (qu.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			int len = qu.size();
			time++;
			// 불 질
			int firelen = fire.size();
			for (int i = 0; i < firelen; i++) {
				Node node = fire.poll();
				int fireX = node.x;
				int fireY = node.y;
				for (int j = 0; j < 4; j++) {
					int rdx = fireX + dx[j];
					int rdy = fireY + dy[j];

					if (rdx < 0 || rdy < 0 || rdx >= N || rdy >= M || map[rdx][rdy]=='#' || map[rdx][rdy]=='F') {
						continue;
					}
					fire.add(new Node(rdx,rdy));
					map[rdx][rdy]='F';
				}

			}
			// 지훈이 이동
			for (int i = 0; i < len; i++) {
				Node jihun = qu.poll();
				int x = jihun.x;
				int y = jihun.y;
				if (x == 0 || y == 0 || x == N - 1 || y == M - 1) {
					System.out.println(time);
					return;
				}
				for (int j = 0; j < 4; j++) {
					int rdx = x + dx[j];
					int rdy = y + dy[j];

					if (map[rdx][rdy] != '.' || rdx < 0 || rdy < 0 || rdx >= N || rdy >= M ) {
						continue;
					}
					map[rdx][rdy] = 'J';
					qu.add(new Node(rdx, rdy));
				}

			}
		}

	}
}
