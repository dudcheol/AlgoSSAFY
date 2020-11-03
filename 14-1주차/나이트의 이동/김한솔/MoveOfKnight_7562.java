/**
 * Backjoon - 7562. 나이트의 이동
 * MoveOfKnight_7562.java
 * @date 2020-11-03
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoveOfKnight_7562 {

	static int l, curX, curY, destX, destY, cnt;
	static int[] dx = {-2,-1,1,2,2,1,-1,-2};
	static int[] dy = {1,2,2,1,-1,-2,-2,-1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=T; tc++) {
			l = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			curX = Integer.parseInt(st.nextToken());
			curY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			destX = Integer.parseInt(st.nextToken());
			destY = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			visited = new boolean[l][l];
			bfs();
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {curX, curY, 0});
		visited[curX][curY] = true;
		
		while(!q.isEmpty()) {
			int[] curInfo = q.poll();
			
			int x = curInfo[0];
			int y = curInfo[1];
			int count = curInfo[2];
			
			for(int i=0; i<8; i++) {
				
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx<0 || nx>=l || ny<0 || ny>=l || visited[nx][ny]) continue;
				
				if(nx == destX && ny == destY) {
					cnt = count+1;
					return;
				}
			
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny, count+1});
			}
		}
	}

}
