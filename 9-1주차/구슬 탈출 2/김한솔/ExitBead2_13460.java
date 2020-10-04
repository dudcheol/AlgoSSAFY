/**
 * Backjoon - 13460. 구슬 탈출 2
 * ExitBead2_13460.java
 * @date 2020-10-04
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Board {
	int[] redBall = new int[2];
	int[] blueBall = new int[2];
	int time;
	
	public Board(int[] redBall, int[] blueBall, int time) {
		this.redBall = redBall;
		this.blueBall = blueBall;
		this.time = time;
	}
}

public class ExitBead2_13460 {

	static int N, M;
	static char[][] map;
	static int blueX, blueY, redX, redY, answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String data = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = data.charAt(j);
				if(map[i][j] == 'R') {
					redX = i;
					redY = j;
				}
				if(map[i][j] == 'B') {
					blueX = i;
					blueY = j;
				}
			}
		}
		
		answer = bfs();
		
		System.out.println(answer);
	}

	private static int bfs() {
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		boolean[][][][] redVisited = new boolean[10][10][10][10];
		//boolean[][] blueVisited = new boolean[10][10];
		redVisited[redX][redY][blueX][blueY] = true;
		//blueVisited[blueX][blueY] = true;
		
		Queue<Board> boards = new LinkedList<>();
		boards.add(new Board(new int[] {redX, redY}, new int[] {blueX, blueY}, 0)); // 빨간공, 파란공의 위치, 횟수
		
		int result = -1;
		while(!boards.isEmpty()) {
			Board board = boards.poll();
			
			// System.out.println(board.redBall[0] +" " + board.redBall[1] + " " + board.blueBall[0] + " " + board.blueBall[1]);
			// 횟수가 10회가 넘은 경우
			if(board.time > 10) break;
			
			// 빨간 공은 구멍으로 들어가고 파란 공은 들어가지 못한 경우 ---> 현재 횟수 
			if(map[board.redBall[0]][board.redBall[1]] == 'O' && map[board.blueBall[0]][board.blueBall[1]] != 'O') {
				result = board.time;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nxRed = board.redBall[0];
				int nyRed = board.redBall[1];
				int nxBlue = board.blueBall[0];
				int nyBlue = board.blueBall[1];
				
				// 현재위치가 구멍이거나 벽이 아닌경우 계속이동
				while(map[nxRed][nyRed] != '#' && map[nxRed][nyRed] != 'O') {
					nxRed += dx[i];
					nyRed += dy[i];
				}
				
				// 현재위치가 벽인 경우 한칸 반대로 이동
				if(map[nxRed][nyRed] == '#') {
					nxRed -= dx[i];
					nyRed -= dy[i];
				}
				
				// 현재위치가 구멍이거나 벽이 아닌경우 계속이동
				while(map[nxBlue][nyBlue] != '#' && map[nxBlue][nyBlue] != 'O') {
					nxBlue += dx[i];
					nyBlue += dy[i];
				}
				
				// 현재위치가 벽인 경우 한칸 반대로 이동
				if(map[nxBlue][nyBlue] == '#') {
					nxBlue -= dx[i];
					nyBlue -= dy[i];
				}
				
				// 빨간 공과 파란 공이 같은 공간에 위치한 경우 --> 어떤 공이 더 멀리서 왔는지 탐색 후 그 공을 한 칸 반대로 이동
				if(nxRed == nxBlue && nyRed == nyBlue && map[nxRed][nyRed] != 'O') {
					int rDist = Math.abs(nxRed-board.redBall[0]) + Math.abs(nyRed-board.redBall[1]);
					int bDist = Math.abs(nxBlue-board.blueBall[0]) + Math.abs(nyBlue-board.blueBall[1]);
					
					if(rDist > bDist) {
						nxRed -= dx[i];
						nyRed -= dy[i];
					} else {
						nxBlue -= dx[i];
						nyBlue -= dy[i];
					}
				}
				
				// 두 구슬중에 한 곳이라도 방문된 적이 없으면 방문 표기
				if(!redVisited[nxRed][nyRed][nxBlue][nyBlue]) {
					redVisited[nxRed][nyRed][nxBlue][nyBlue] = true;
					//blueVisited[nxBlue][nyBlue] = true;
					boards.add(new Board(new int[] {nxRed, nyRed}, new int[] {nxBlue, nyBlue}, board.time+1));
				}
			}
			
		}
		
		return result;
	}

}
