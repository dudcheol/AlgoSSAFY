/**
 * Backjoon - 6087. 레이저 통신
 * 레이저통신_6087.java
 * @date 2020-12-28
 * @author hansolKim
 **/

package p6000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 레이저통신DFS_6087 {
	
	static int w, h, answer, cnt;
	static int[] dx = {-1,0,1,0}; // 상, 우, 하, 좌
	static int[] dy = {0,1,0,-1};
	static char[][] map;
	static int[][] raisers;
	static int[][] costs;
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new char[h][w];
		raisers = new int[2][2];
		int raiserIdx = 0;
		
		for(int i=0; i<h; i++) {
			String data = br.readLine();
			for(int j=0; j<w; j++) {
				map[i][j] = data.charAt(j);
				if(map[i][j] == 'C') {
					raisers[raiserIdx][0] = i;
					raisers[raiserIdx++][1] = j;
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		costs = new int[h][w];
		cnt = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				costs[i][j] = h*w;
			}
		}
		
		costs[raisers[0][0]][raisers[0][1]] = 0;
		for(int i=0; i<4; i++) {
			installMirror(raisers[0][0], raisers[0][1], i, 0);
		}
		
		System.out.println(answer);
		System.out.println(cnt);
	}

	private static void installMirror(int x, int y, int dir, int cost) {
		
		if(answer <= cost) { return; }
		cnt++;
		
		if(x == raisers[1][0] && y == raisers[1][1]) {
			answer = costs[x][y];
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			// 영역 밖이거나 벽이거나 반대방향인 경우는 패스
			if(nx<0 || nx>=h || ny<0 || ny>=w || Math.abs(dir-i) == 2 || map[nx][ny] == '*') continue;

			// cost계산
			int newCost = dir==i ? cost : cost+1;
			
			if(newCost < costs[nx][ny]) { // 현재 칸의 비용이 이전에 들어왔던 비용과 같거나 작은 경우에만 진행
				costs[nx][ny] = newCost;
				installMirror(nx, ny, i, newCost);
			}
		}
	}
}