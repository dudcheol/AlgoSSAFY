package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나이트의이동 {
	static int[][] d = { { -1, -2 }, { -2, -1 }, { 1, -2 }, { 2, -1 }, { -2, 1 }, { -1, 2 }, { 2, 1 }, { 1, 2 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < test; t++) {
			int n = Integer.parseInt(br.readLine());
			boolean[][] visit = new boolean[n][n];
			
			Queue<int[]> q = new LinkedList<int[]>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startx = Integer.parseInt(st.nextToken());
			int starty = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int endx = Integer.parseInt(st.nextToken());
			int endy = Integer.parseInt(st.nextToken());

			q.add(new int[] { startx, starty,0 });
			int cost=0;
			
			while (!q.isEmpty()) {
				int[] arr = q.poll();
				int x = arr[0];
				int y = arr[1];
				cost = arr[2];
				
				if (x == endx && y == endy) 
					break;
				
				for (int i = 0; i < 8; i++) {
					int dx = x + d[i][0];
					int dy = y + d[i][1];

					if (dx < 0 || dy < 0 || dx >= n || dy >= n || visit[dx][dy])
						continue;
					visit[dx][dy] = true;
					q.add(new int[] { dx, dy, cost + 1 });
				}
			}
			System.out.println(cost);

		}
	}
}
