/**
 * Backjoon - 10026. 적록색약
 * RedGreenWeak_10026.java
 * @date 2020-11-08
 * @author hansolKim
 **/

package p10000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RedGreenWeak_10026 {

	static int N;
	static int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[][] noramlMap, weakMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		noramlMap = new char[N][N];
		weakMap = new char[N][N];
		
		for(int i=0; i<N; i++) {
			noramlMap[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			weakMap[i] = noramlMap[i].clone();
		}
		
		int normalCnt = 0; // 일반사람
		int weakCnt = 0; // 적록색약
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(noramlMap[i][j] != '.') {
					bfs(i, j, 0);
					normalCnt++;
				}
				if(weakMap[i][j] != '.') {
					bfs(i, j, 1);
					weakCnt++;
				}
			}
		}
		
		System.out.println(normalCnt + " " + weakCnt);
		br.close();
	}

	private static void bfs(int x, int y, int type) {
		
		char[][] map = (type==0) ? noramlMap : weakMap;
		
		// 컬러색
		char color = map[x][y];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y});
		map[x][y] = '.'; // 방문표기
		
		while(!q.isEmpty()) {
			
			int[] xy = q.poll();
			int curX = xy[0];
			int curY = xy[1];
			
			for(int i=0; i<4; i++) {
				int nx = curX + d[i][0];
				int ny = curY + d[i][1];
				
				// 영역 밖이거나 이미 방문한 경우
				if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] == '.') {continue;}
				
				// 일반사람이고 색이 다른 경우
				if(type == 0 && map[nx][ny] != color) {continue;}
				
				// 적록색약이고 색이 다른 경우에서 파란색이 있는 경우 패스
				if(type == 1 && (map[nx][ny] != color && (color=='B' || map[nx][ny]=='B'))) {continue;}
					
				// 해당 칸 방문처리
				map[nx][ny] = '.';
				q.add(new int[] {nx, ny}); 
			}
			
		}
		
	}

}