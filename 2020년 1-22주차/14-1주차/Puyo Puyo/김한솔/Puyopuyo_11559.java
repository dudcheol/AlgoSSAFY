/**
 * Backjoon - 11559. Puyo Puyo
 * Puyopuyo_11559.java
 * @date 2020-11-03
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Puyopuyo_11559 {
	
	static final int H=12, W=6;
	static char[][] map;
	static boolean[][] checked;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[H][W];
		
		for(int i=0; i<H; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int answer = 0;
		
		while(true) {
			
			checked = new boolean[H][W];
			
			boolean isPop = false;
			// 1. 상하좌우로 연결되어 있으면 터뜨리기
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j] != '.' && !checked[i][j]) {popCheck(i, j);}
					if(checked[i][j]) {isPop = true;} // 터질 뿌요가 있는 경우
				}
			}
			
			// 2. 터질 뿌요가 없는 경우
			if(!isPop) {break;}
			
			// 3. 터질 뿌요가 있는 경우			
			// 3-1. 터질 뿌요 터뜨림
			popPuyo();
			
			// 3-2. 공중에 떠있는 뿌요 아래로 내리기
			setArrangePuyo();
			answer++;
		}
		
		System.out.println(answer);
		br.close();
	}

	private static void setArrangePuyo() {
		for(int i=0; i<W; i++) {
			for(int j=H-1; j>=0; j--) {
				if(map[j][i] == '.') {
					for(int k=j-1; k>=0; k--) {
						if(map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break; // 중요
						}
					}
				}
			}
		}
	}

	// 뿌요 터뜨리는 메서드
	private static void popPuyo() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(checked[i][j]) { map[i][j] = '.'; }
			}
		}
	}

	// 터질 뿌요가 있는 지 확인
	private static void popCheck(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> popList = new ArrayList<>();
		boolean[][] visited = new boolean[H][W];
		
		q.add(new int[] {x, y});
		popList.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] curInfo = q.poll();
			int curX = curInfo[0];
			int curY = curInfo[1];
			char curColor = map[x][y]; // 현재위치에 뿌요 색
			
			for(int i=0; i<4; i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				
				if(nx<0 || nx>=H || ny<0 || ny>=W 
						|| map[nx][ny]!=curColor || visited[nx][ny]) continue; 
				
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
				popList.add(new int[] {nx, ny});
			}
			
		}
		
		// 터질 뿌요가 4개이상인 경우
		if(popList.size() >= 4) {
			for(int i=0; i<popList.size(); i++) {
				int popX = popList.get(i)[0];
				int popY = popList.get(i)[1];
				
				checked[popX][popY] = true;
			}
		}
	}

}
