/**
 * Backjoon - 2573. 빙산
 * 빙산_2573.java
 * @date 2021-01-27
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산_2573 {

	static int R, C, year, div;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		year = 0;
		
		while(true) {
			// 1. 빙산이 분리되어 있는 지 확인
			div = 0;
			checked = new boolean[R][C];
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j]>0 && !checked[i][j]) {
						bfs(i, j);
						if(++div>=2) { break; }
					}
				}
			}
			
			if(div != 1) { break; }
			
			year++;
			
			// 2. 빙산이 녹는 과정
			melt();
		}
		
		System.out.println(div == 0 ? 0 : year);
		br.close();
	}

	private static void bfs(int x, int y) {
		checked[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.poll()[1];
			
			for(int i=0; i<4; i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				
				if(nx<0 || nx>=R || ny<0 || ny>=C 
						|| map[nx][ny] == 0 || checked[nx][ny]) continue;
				
				checked[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
	}

	private static void melt() {
		int[][] tempMap = new int[R][C];
		
		// 기존 배열 복사
		for(int i=0; i<R; i++) {
			tempMap[i] = map[i].clone();
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(tempMap[i][j]!=0) { // 빙산이 있는 경우
					for(int k=0; k<4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						
						if(nx<0 || nx>=R || ny<0 || ny>=C || map[nx][ny]>0) continue;
						
						if(tempMap[i][j] == 0) break;
						tempMap[i][j] = tempMap[i][j]-1;
					}
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			map[i] = tempMap[i].clone();
		}
	}
}