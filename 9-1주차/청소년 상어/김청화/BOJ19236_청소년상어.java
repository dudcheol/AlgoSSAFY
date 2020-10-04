package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 19236 청소년 상어 https://www.acmicpc.net/problem/19236
public class BOJ19236_청소년상어 {
	static int[][] dirs = { {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1} };
	static int result;
	static int[][] map; //물고기의 번호 저장
	static fish[] order; // order[0]에는 상어의 위치 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int eat = 0;
		// map에 초기 상태 입력
		map = new int[4][4]; //물고기의 번호 저장
		order = new fish[17]; // order[0]에는 상어의 위치 저장
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				map[i][j] = no;
				order[no] = new fish(i, j, dir);	
				
				if(i == 0 && j == 0) {
					eat = no;
					order[no] = new fish(0, 0, -1); // 해당 위치 물고기는 없어짐
					map[0][0] = -1; // 상어가 있는 칸 -1
					order[0] = new fish(i, j, dir); // 초기 상어 위치와 방향 저장
				}
			}
		}
		
		// copy 배열(cp_order[i] = order[i]하면 같은 주소값을 가짐)
		int[][] copy = new int[4][4];
		fish[] cp_order = new fish[17];
		for (int i = 0; i < 17; i++) {
			cp_order[i] = new fish(order[i].x, order[i].y, order[i].dir);
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// 상어 이동
		check(copy, cp_order, order[0], eat);

		System.out.println(result);
	}
	
	
	private static void fmove(int[][] copy, fish[] cp_order) {
		for (int i = 1; i < 17; i++) {
			
			if(cp_order[i].dir != -1) { // 해당 번호의 물고기가 없지 않으면
				fish cur = cp_order[i];
				int x = cp_order[i].x;
				int y = cp_order[i].y;
				int dir = cp_order[i].dir;
				
				for (int j = 0; j < 8; j++) {
					dir %= 8;
					
					int nx = x + dirs[dir][0];
					int ny = y + dirs[dir][1];
					
					if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny] == -1) { // 상어가 있거나 공간의 경계를 넘는 칸만 이동 불가능
						dir++;
						continue;
					}
					
					// 이동할 수 있으면 서로 위치를 바꿈
					if(copy[nx][ny] == 0){ // 빈칸이면 
						copy[x][y] = 0; // 이전 칸이 빈 칸이 됨
						copy[nx][ny] = i;
						cp_order[i] = new fish(nx, ny, dir);
						break;
						
					} else if(copy[nx][ny] > 0) { // 물고기가 있으면
						int t = copy[nx][ny];
						copy[nx][ny] = copy[cur.x][cur.y];
						copy[cur.x][cur.y] = t;
						
						cp_order[i] = new fish(nx, ny, dir);
						cp_order[t] = new fish(cur.x, cur.y, cur.dir);
						break;
					}			
				} 
			}
		}
	}

	// eat = 여태까지 먹은 물고기 번호의 합
	private static void check(int[][] copy, fish[] cp_order, fish s, int eat) {
		// 이전 상태
//		// copy 배열(cp_order[i] = order[i]하면 같은 주소값을 가짐)
//		int[][] copy = new int[4][4];
//		fish[] cp_order = new fish[17];
//		for (int i = 0; i < 17; i++) {
//			cp_order[i] = new fish(order[i].x, order[i].y, order[i].dir);
//		}
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				copy[i][j] = map[i][j];
//			}
//		}
		
		// 번호가 작은 물고기부터 순서대로 이동
		fmove(copy, cp_order);

		int x = s.x;
		int y = s.y;
		int dir = s.dir;
		boolean flag = false;
		
		// 상어가 이동가능한 최대 길이 3
		int i = 3;
		while(i > 0) {
			int nx = x + i*dirs[dir][0];
			int ny = y + i*dirs[dir][1];
			
			// 상어는 물고기가 없는 칸으로 이동할 수 없음
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || copy[nx][ny] == 0) {
				i--;
				continue;
			}
			
			int no = copy[nx][ny];
			if(copy[nx][ny] > 0) {
				flag = true;
				
				///////////////////////////////////////////////
				copy[x][y] = 0; // 빈 칸으로
				fish shark = new fish(nx, ny, cp_order[copy[nx][ny]].dir); // 상어 위치 저장
				cp_order[copy[nx][ny]] = new fish(0, 0, -1); // 물고기 먹힘
				copy[nx][ny] = -1; // 상어 있는 칸으로
				cp_order[0] = shark; // 상어 위치, 방향 업데이트
				
				check(copy, cp_order, cp_order[0], eat + no);
				
				// 재귀 돌리기 전으로 다시 roll back
				copy[nx][ny] = no;
				cp_order[no] = new fish(nx, ny, shark.dir);
				copy[x][y] = -1; // 원래 상어가 있던 자리
				/////////////////////////////////////////////
			}
			i--;
		}
		
		if(!flag) { // 더 이상 이동할 수 없으면
			if(result < eat)
				result = eat;
		}
		
	}


	static class fish{
		int x, y;
		int dir;
		
		fish(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}
