package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B11559 {
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[13][6];
		boolean[][] visited = new boolean[13][6];
		for (int i = 1; i < 13; i++) {
			String input = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[0][j] = '.';
				visited[0][j] = true;
				map[i][j] = input.charAt(j);
			}
		}

		Queue<Node> qu = new LinkedList<Node>();
		Queue<Node> initNode = new LinkedList<Node>();
		Queue<Node> removeNode = new LinkedList<Node>();
		int solve = 0;

		while (true) {
			
			
			for (int i = 1; i < 13; i++) {
				for (int j = 0; j < 6; j++) {
					int count = 0;
					if (map[i][j] != '.' && !visited[i][j]) {

						qu.add(new Node(i, j));
						visited[i][j] = true;
						initNode.add(new Node(i, j));
						count += 1;
						while (!qu.isEmpty()) {
							Node node = qu.poll();
							int x = node.x;
							int y = node.y;

							for (int a = 0; a < 4; a++) {
								int rdx = x + dx[a];
								int rdy = y + dy[a];

								if (rdx < 1 || rdy < 0 || rdx >= 13 || rdy >= 6 || visited[rdx][rdy]
										|| map[rdx][rdy] != map[x][y]) {
									continue;
								}

								// 칠해준다
								visited[rdx][rdy] = true;
								qu.add(new Node(rdx, rdy));

								initNode.add(new Node(rdx, rdy));
								count++;

							}
						}
						// 터진다.
						if (count >= 4) {
							// 터뜨려줌
							while (!initNode.isEmpty()) {
								removeNode.add(initNode.poll());

							}
						}
						// 터지지 않는다.
						else {
							// 칠해놨던 곳 다시 원상복구
							while (!initNode.isEmpty()) {
								Node in = initNode.poll();
								int XX = in.x;
								int YY = in.y;
								visited[XX][YY] = false;
							}
						}
					}
				}
			}
			if(removeNode.isEmpty()) {
				break;
			}
			while (!removeNode.isEmpty()) {
				Node in = removeNode.poll();
				int XX = in.x;
				int YY = in.y;

				visited[XX][YY] = false;
				map[XX][YY] = '.';
			}
			for (int c = 0; c < 72; c++) {
				fall();
			}
			solve++;
		}

		System.out.println(solve);
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static public void fall() {
		for (int i = 12; i > 0; i--) {
			for (int j = 5; j > -1; j--) {
				if (map[i][j] == '.' && map[i - 1][j] != '.') {
					map[i][j] = map[i - 1][j];
					map[i - 1][j] = '.';
				}
			}
		}

	}
}
