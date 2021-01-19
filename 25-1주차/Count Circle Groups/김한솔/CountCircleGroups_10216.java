/**
 * Backjoon - 10216. CountCircleGroups
 * CountCircleGroups_10216.java
 * @date 2021-01-19
 * @author hansolKim
 **/

package p10000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CountCircleGroups_10216 {
	
	static int[][] map, init;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int limit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			
			int N = Integer.parseInt(br.readLine());
			
			Queue<int[]> q = new LinkedList<>();
			map = new int[10001][10001];
			init = new int[10001][10001];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				q.add(new int[] {x, y, r+1});
				map[x][y] = r+1;
				init[x][y] = 1;
			}			
			
			while(!q.isEmpty()) {
				int curX = q.peek()[0];
				int curY = q.peek()[1];
				int curR = q.poll()[2];
				
				for(int i=0; i<4; i++) {
					int nx = curX + dx[i];
					int ny = curY + dy[i];
					
					if(nx<0 || nx>10000 || ny<0 || ny>10000 || map[nx][ny] >= curR) continue;
					
					if(curR-1 == 0) { continue;} 
					map[nx][ny] = curR-1;
					q.add(new int[] {nx, ny, curR-1});
				}
			}
			
			int answer = 0;
			limit = N;
			for(int i=0; i<10001; i++) {
				for(int j=0; j<10001; j++) {
					if(map[i][j] == 0) continue;
					bfs(i, j);
					answer++;
					if(limit == 0) { break; }
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		map[x][y] = 0;
		
		while(!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.poll()[1];
			
			if(init[curX][curY] == 1) { limit--;}
			
			for(int i=0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				if(nx<0 || nx>10000 || ny<0 || ny>10000 || map[nx][ny] == 0) continue;
				
				map[nx][ny] = 0;
				q.add(new int[] {nx, ny});
			}
		}
	}
}