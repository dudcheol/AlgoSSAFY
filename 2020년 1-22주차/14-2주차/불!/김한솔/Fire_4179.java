/**
 * Backjoon - 4179. 불
 * Fire_4179.java
 * @date 2020-11-06
 * @author hansolKim
 **/

package p4000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire_4179 {
	
	static int R, C, jX, jY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		q = new LinkedList<>();
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					jX = i; jY = j;
					map[i][j] = '.';
				}
				if(map[i][j] == 'F') {
					q.add(new int[] {i,j,1,0}); // (x,y,type,cnt)
				}
			}
		}
		
		int result = exit();
		String answer = result==-1?"IMPOSSIBLE":String.valueOf(result);
		System.out.println(answer);
		br.close();
	}

	private static int exit() {
		
		q.add(new int[] {jX, jY, 0, 0});
		visited[jX][jY] = true;
		
		while(!q.isEmpty()) {
			int[] info = q.poll();
			
			int x = info[0];
			int y = info[1];
			int type = info[2];
			int cnt = info[3];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				// 지훈인 경우
				if(type == 0) {
					
					// 탈출에 성공한 경우
					if(nx<0 || nx>=R || ny<0 || ny>=C) {return cnt+1;}
						
					// 빈 칸인 경우
					if(!visited[nx][ny] && map[nx][ny] == '.') {
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny, type, cnt+1});
					}
				} else { // 불인 경우
					
					// 영역 밖이거나 벽인 경우
					if(nx<0 || nx>=R || ny<0 || ny>=C || map[nx][ny] != '.') continue;
					
					map[nx][ny] = 'F';
					q.add(new int[] {nx, ny, type, cnt+1});
				}
			}
		}
		return -1;
	}

}
