package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준 3190 뱀 https://www.acmicpc.net/problem/3190
public class BOJ3190_뱀 {
	static int N, K, L, length = 1, X[], dir = 1, second = 1;
	static int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	
	static char[] C;
	static int[][] board;
	static LinkedList<int[]> snake = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); 
		K = Integer.parseInt(br.readLine()); // 사과 개수
		
		board = new int[N][N];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			board[i - 1][j - 1] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		X = new int[L];
		C = new char[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			X[i] = Integer.parseInt(st.nextToken());
			C[i] = st.nextToken().charAt(0);		
		}
		
		/////////////////////////////////////////////
		
		// 처음 길이 1
		// 사과 없으면 몸길이를 줄이고 머리쪽 한 칸 증가 시켜서 이동
		// 사과를 먹으면 몸의 길이 1 증가 -> 그대로에 머리를 한 칸 증가시키면 됨 (사과를 없애고)
		// 벽에 부딫히거나 몸에 닿으면 게임 오버
		int idx = 0;
		int nx = 0, ny = 0; // 머리
		snake.add(new int[] {0, 0});
		while(true) {
			
			// 현재 좌표
			nx += dirs[dir % 4][0];
			ny += dirs[dir % 4][1];
			
			// 범위 밖이면 
			if(nx < 0 || ny < 0 || nx >= N || ny >= N)
				break;
			
			// 몸통이랑 닿으면
			if(!check(nx, ny))
				break;		
			
			snake.add(new int[] {nx, ny});

			// 사과 있으면 그대로에 머리만 한칸 이동 (그니까 그냥 냅둬)
			// 사과 없으면 tail에서 좌표 하나 빼줘야 함
			if(board[nx][ny] == 2)
				board[nx][ny] = 0;
			else if(board[nx][ny] != 2) 
				snake.remove(0);
			
//			print();	
			
			// 해당 초가 지난 뒤(모든 수행을 마친 뒤 방향 전환)
			if(idx < L && X[idx] == second) {
				if(C[idx] == 'D') dir += 1;
				else if(C[idx] == 'L') dir -= 1;
				idx++;
			}
			if(dir == -1) dir = 3; 
			
			second++;
		}
		
		System.out.println(second);
	}

	// 몸통이랑 닿는지 체크
	private static boolean check(int x, int y) {
		for (int i = 0; i < snake.size(); i++) {
			if(snake.get(i)[0] == x && snake.get(i)[1] == y)
				return false;
			
		}
		return true;
	}
	
//	private static void print() {
//		System.out.println();
//		System.out.println("=================================");
//		System.out.println(second);
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				boolean flag = false;
//				for (int k = 0; k < snake.size(); k++) {
//					if(snake.get(k)[0] == i && snake.get(k)[1] == j) {
//						System.out.print(1 + " ");
//						flag = true;
//						break;
//					}
//				}
//				if(!flag)
//					System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
}
