package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 백준 11559 Puyo Puyo https://www.acmicpc.net/problem/11559
public class BOJ11559_PuyoPuyo2 {
	static int totalCnt, cnt;
	static int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	static char[][] map = new char[12][6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < 6; j++) {
				map[i][j] = tmp[j];
			}
		}
		
		while(true) {
			boolean flag = false;
			Queue<int[]> puyo = new LinkedList<>();
			boolean[][] visited = new boolean[12][6];
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j] != '.') {
						cnt = 0;
						Queue<int[]> tmp = new LinkedList<>();
						dfs(tmp, visited, map[i][j], i, j);				
						if(cnt >= 4) {
							int size = tmp.size();
							for (int k = 0; k < size; k++) {
								puyo.add(tmp.poll());
							}
							flag = true;
						}
					}					

				}
			}					
			if(flag) {
				totalCnt++;
				bomb(puyo);
			} else
				break;
		}
		System.out.println(totalCnt);
	}

	private static void dfs(Queue<int[]> tmp, boolean[][] visited, char color, int x, int y) {
		
		tmp.add(new int[] {x, y});
		visited[x][y] = true;
		cnt++;
		
		for (int d = 0; d < dirs.length; d++) {
			int nx = x + dirs[d][0];
			int ny = y + dirs[d][1];
			
			if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || map[nx][ny] != color || visited[nx][ny])
				continue;
			
			dfs(tmp, visited, color, nx, ny);
		}

		return;
	}

	private static void bomb(Queue<int[]> tmp) {
		int size = tmp.size();
		for (int i = 0; i < size; i++) {
			int x = tmp.peek()[0];
			int y = tmp.poll()[1];
			
			map[x][y] = '.'; // 지워주기
		}
		
		move();
	}

	private static void move() {
		Stack<Character> line = new Stack<>();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 12; j++) {
				if(map[j][i] != '.') {
					line.add(map[j][i]);
					map[j][i] = '.';
				}
			}
			
			int j = 11;
			while(!line.isEmpty()) {
				map[j][i] = line.pop();
				j--;
			}
		}
	}

}
