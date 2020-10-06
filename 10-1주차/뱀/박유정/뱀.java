package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀 {
	static int n, apple;
	static int[][] map;
	static final int APPLE = Integer.MAX_VALUE;
	static Queue<Move> q = new LinkedList<Move>();
	static Queue<int[]> snake = new LinkedList<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		apple = Integer.parseInt(br.readLine());

		for (int i = 0; i < apple; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = APPLE;
		}

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken())+1;//x초 뒤에 움직이는 것이기 때문에 +1
			String dir = st.nextToken();
			q.add(new Move(time, dir));//시간 방향넣어주기
		}
		
		Move move = q.poll();//첫번째 움직임
		int time = move.time;
		String dir = move.dir;

		int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };//오른쪽 아래 왼쪽 위 90도씩 회전
		int i = 0;//오른쪽부터 시작

		map[0][0] = 1;//시작점
		snake.add(new int[] { 0, 0 });
		
		int x = 0, y = 0, count = 0;

		while (true) {
			count++;

			if (count == time) {//움직일 시간
				if (dir.equals("L")) {
					// 왼쪽
					i--;
					if (i < 0) {
						i = 3;
					}
				} else if (dir.equals("D")) {
					// 오른쬭
					i++;
					if (i >= 4)
						i = 0;
				}
				if (!q.isEmpty()) {
					Move a = q.poll();
					time = a.time;
					dir = a.dir;
				}
			}

			x += d[i][0];
			y += d[i][1];

			if (x < 0 || y < 0 || x >= n || y >= n || map[x][y] == 1) {// 벽이거나 뱀이 있거나
				break;
			}
			if (map[x][y] != APPLE) {//사과 없으면
				int[] a = snake.poll();//꼬리 짜르기
				map[a[0]][a[1]] = 0;//빈공간 만들기
			}

			snake.add(new int[] { x, y });

			map[x][y] = 1;
//			print();
		}
		System.out.println(count );
	}
//
//	static void print() {
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//	}
}

class Move {
	int time;
	String dir;

	public Move(int time, String dir) {
		super();
		this.time = time;
		this.dir = dir;
	}
}
