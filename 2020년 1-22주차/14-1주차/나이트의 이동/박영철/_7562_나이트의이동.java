package week14;

import java.util.*;
import java.io.*;

public class _7562_나이트의이동 {

	static int L, map[][], cy, cx;
	static int[] dy = {-1,-2,-2,-1,1,2,2,1};
	static int[] dx = {-2,-1,1,2,2,1,-1,-2};
	static boolean[][] visited;
	private static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < TC; test_case++) {
			L = Integer.parseInt(br.readLine());
			map = new int[L][L];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cy = Integer.parseInt(st.nextToken());
			cx = Integer.parseInt(st.nextToken());
			visited = new boolean[L][L];
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
					= 1;
			
			Queue<int[]> q = new LinkedList<>();
			
			q.offer(new int[] {cy, cx, 0});
			visited[cy][cx] = true;
			
			while(!q.isEmpty()) {
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				int cnt = p[2];
				
				if(map[y][x] == 1) {
					answer = cnt;
					break;
				}
				
				for (int d = 0; d < 8; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0||nx<0||ny>=L||nx>=L||visited[ny][nx]) continue;
					visited[ny][nx] = true;
					q.offer(new int[] {ny,nx,cnt+1});
				}
			}
			
			sb.append(answer).append('\n');
		}
		System.out.print(sb);
	}

}
