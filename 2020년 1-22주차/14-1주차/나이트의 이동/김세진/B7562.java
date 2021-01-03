package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562 {
	static int[] dx = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static int[] dy = { -1, 1, 2, 2, 1, -1, -2, -2 };

	static class knight {
		int x;
		int y;

		public knight(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			Queue<knight> qu = new LinkedList<knight>();

			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			StringTokenizer stz = new StringTokenizer(br.readLine());

			int startX = Integer.parseInt(stz.nextToken());
			int startY = Integer.parseInt(stz.nextToken());
			stz = new StringTokenizer(br.readLine());
			int goalX = Integer.parseInt(stz.nextToken());
			int goalY = Integer.parseInt(stz.nextToken());

			int moveCount = 0;
			qu.add(new knight(startX, startY));
			visited[startX][startY] = true;

			loop:while (!qu.isEmpty()) {
				int len = qu.size();

				for (int i = 0; i < len; i++) {
					knight k=qu.poll();
					int x=k.x;
					int y=k.y;
					if(x==goalX && y==goalY) {
						break loop;
					}
					for(int j=0;j<8;j++) {
						int rdx=x+dx[j];
						int rdy=y+dy[j];
						
						if(rdx<0 || rdy<0 || rdx>=N ||rdy>=N || visited[rdx][rdy]) {
							continue;
						}
						
						visited[rdx][rdy]=true;
						qu.add(new knight(rdx, rdy));
						
					}
					
				}
				moveCount++;
			}
			System.out.println(moveCount);
		}

	}

}
