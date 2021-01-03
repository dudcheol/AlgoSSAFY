package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562_나이트의이동 {
	static int T, I, cur_x, cur_y, next_x, next_y, result;
	static int[][] dirs = { {-1, - 2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2} }; // 나이트 이동방향
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			I = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());			
			cur_x = Integer.parseInt(st.nextToken());
			cur_y = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			next_x = Integer.parseInt(st.nextToken());
			next_y = Integer.parseInt(st.nextToken());

			visited = new boolean[I][I];
			
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {cur_x, cur_y, 0});
			
			loop: while(!q.isEmpty()) {
				int x = q.peek()[0];
				int y = q.peek()[1];
				int cnt = q.poll()[2];
				
				if(x == next_x && y == next_y) {
					result = cnt;
					break loop;
				}			
					
				for (int d = 0; d < dirs.length; d++) {
					int nx = x + dirs[d][0];
					int ny = y + dirs[d][1];
					
					if(nx < 0 || ny < 0 || nx >= I || ny >= I || visited[nx][ny])
						continue;
								
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny, cnt + 1});
				}
			}
			
			System.out.println(result);
		}
	}
}
