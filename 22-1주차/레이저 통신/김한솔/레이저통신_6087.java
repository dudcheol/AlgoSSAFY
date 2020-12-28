/**
 * Backjoon - 6087. 레이저 통신
 * 레이저통신_6087.java
 * @date 2020-12-28
 * @author hansolKim
 **/

package p6000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 레이저통신_6087 {
	
	static int w, h, answer;
	static int[] dx = {-1,0,1,0}; // 상, 우, 하, 좌
	static int[] dy = {0,1,0,-1};
	static char[][] map;
	static int[][] raisers;
	static int[][] visited;
			
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
		visited = new int[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				visited[i][j] = h*w;
			}
		}
		
		installMirror();
		System.out.println(answer);
	}

	private static void installMirror() {
		Queue<int[]> q = new LinkedList<>(); // 좌표, 방향, 거울 개수
		q.add(new int[] {raisers[0][0], raisers[0][1], -1, 0});
		visited[raisers[0][0]][raisers[0][1]] = 0; // 방문처리
		
		while(!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.peek()[1];
			int dir = q.peek()[2];
			int mirrorCnt = q.poll()[3];
			
			for(int i=0; i<4; i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				int nextMirrorCnt = mirrorCnt;
				
				// 벽이거나 영역 밖인 경우 패스
				if(nx<0 || nx>=h || ny<0 || ny>=w || map[nx][ny] == '*') {continue;}
				
				// 방향이 반대방향일때 패스
				if(dir != -1 && Math.abs(dir-i) == 2) { continue;}
				
				if(dir!=-1 && dir!=i) { nextMirrorCnt += 1; }
				
				// 추가한 거울 개수가 현재 최소인 거울 개수보다 많은 경우 패스
				if(nextMirrorCnt > answer) { continue; }
				
				// 해당 칸을 이전에 방문했고 거울 개수가 더 많으면 패스
				if(visited[nx][ny] <= nextMirrorCnt) { continue; }												
				
				// 해당 위치가 레이저인 경우
				if(nx == raisers[1][0] && ny == raisers[1][1]) {
					answer = nextMirrorCnt;
					continue;
				}
				
				// 해당 위치 추가
				visited[nx][ny] = nextMirrorCnt;
				
				q.add(new int[] {nx, ny, i, nextMirrorCnt});
			}
		}
	}

}
