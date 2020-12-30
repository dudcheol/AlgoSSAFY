package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087_레이저통신 {
	static int W, H, answer = Integer.MAX_VALUE, visited[][], dirs[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	static char[][] map;
	static ArrayList<int[]> list = new ArrayList<>();
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			String string = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = string.charAt(j);
				
				if(map[i][j] == 'C') 
					list.add(new int[] {i, j}); // x좌표, y좌표, 방향, cnt(방향 바뀐 수)					
			
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int[] start = list.get(0);
		int[] end = list.get(1);
		visited[start[0]][start[1]] = 0;
		q.add(new int[] {start[0], start[1], -1, 0});
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int dir = q.peek()[2];
			int cnt = q.poll()[3];
			
			if(x == end[0] && y == end[1]) {
				answer = Math.min(cnt, answer);
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nx = x + dirs[d][0];
				int ny = y + dirs[d][1];
				
				if(nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == '*')
					continue;
				
				int count = cnt;
				// 이전 좌표와 다르거나 C가 아니면
				if(dir != d && dir != -1)
					count++;
				
				if(visited[nx][ny] < count)
					continue;
			
				visited[nx][ny] = count;
				q.add(new int[] {nx, ny, d, count});
			}
		}
		System.out.println(answer);
	}
}
