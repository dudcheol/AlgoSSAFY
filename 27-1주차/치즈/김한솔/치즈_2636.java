/**
 * Backjoon - 2636. 치즈
 * 치즈_2636.java
 * @date 2021-02-01
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_2636 {

	static int R, C, cnt, time;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static boolean[][] visited;
	
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
		
		time = 0; // 모두 녹아서 없어지는 데 걸리는 시간
		cnt = 0; // 한 시간 전에 남아있는 치즈조각
		
		while(true) {
			
			// 1. 치즈가 모두 녹았는 지 확인
			if(isAllMelt()) { break;}
			
			// 2. 치즈 구멍 확인 후 따로 표기
			checkHoles();
			
			// 3. 녹기전 치즈 --> 치즈조각 카운트
			cnt = countCheeze();
			
			// 4. 치즈가 녹음
			meltCheeze();
			
			// 5. 시간 증가
			time++;
		}
		
		System.out.println(time);
		System.out.println(cnt);
		br.close();
	}

	private static void meltCheeze() {
		
		int[][] tempMap = new int[R][C];
		
		for(int i=0; i<R; i++) { tempMap[i] = map[i].clone();}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				// 치즈인 경우 -> 녹는 치즈인지 확인
				if(map[i][j] == 1) { 
					// 4방에 공기가 있는지 확인
					for(int k=0; k<4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						
						if(map[nx][ny] == 0) { 
							tempMap[i][j] = 0; // 녹음
							break;
						}
					}
				}
			}
		}
		
		for(int i=0; i<R; i++) { map[i] = tempMap[i].clone();}
	}

	private static int countCheeze() {
		
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 1) cnt++; // 치즈가 있는 경우
			}
		}
		return cnt; // 치즈가 아무것도 없는 경우
	}

	private static void checkHoles() {
		
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 가장자리는 확인할 필요 없으므로 패스
				if(i==0 || i==R-1 || j==0 || j==C-1) continue;
				
				// 방문한 적 없고 치즈가 아닌 경우
				if(!visited[i][j] && map[i][j] != 1) {
					bfs(i, j);
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> mem = new LinkedList<>();
		q.add(new int[] {x, y});
		mem.add(new int[] {x, y});
		visited[x][y] = true;
		
		boolean isAir = false; // 공기인지 아닌 지 확인
		
		while(!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.poll()[1];
			
			for(int i=0; i<4; i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				
				// 치즈이거나 방문했던 곳인 경우
				if(map[nx][ny] == 1 || visited[nx][ny]) continue;
				
				// 벽에 도달한 경우
				if(nx == 0 || nx == R-1 || ny == 0 || ny == C-1) {
					isAir = true;
					continue;
				}
				
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
				mem.add(new int[] {nx, ny});
			}
		}
		
		// x, y로 이어진 모든 좌표들은 공기 아니면 치즈구멍이다
		while(!mem.isEmpty()) {
			int curX = mem.peek()[0];
			int curY = mem.poll()[1];
			
			map[curX][curY] = isAir ? 0 : 2;
		}
	}

	private static boolean isAllMelt() {
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 1) return false; // 치즈가 있는 경우
			}
		}
		return true; // 치즈가 아무것도 없는 경우
	}
}