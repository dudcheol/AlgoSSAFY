package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14499 주사위 굴리기 https://www.acmicpc.net/problem/14499
public class BOJ14499_주사위굴리기 {
	static int N, M, x, y, K;
	static int[][] map, dirs = { {0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0} }; // 동서북남 순
	static int[] cube = { 0, 0, 0, 0, 0, 0 }; // 주사위 초기 상태 { 6, 3, 4, 2, 5, 1}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder string = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int d;
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {
			d = Integer.parseInt(st.nextToken());

			x += dirs[d][0];
			y += dirs[d][1];
			
			if(x < 0 || y < 0 || x >= N || y >= M) {
				x -= dirs[d][0];
				y -= dirs[d][1];
				continue;
			}
			
			rolling(d);
			
			if(map[x][y] == 0) { // 해당 칸이 0이라면 주사위 바닥면에 쓰인 수가 복사 됨
				map[x][y] = cube[0];
			
			} else if(map[x][y] != 0) { // 0이 아니면 칸에 쓰인 수가 주사위 바닥면에 복사 & 해당 칸 0으로
				cube[0] = map[x][y];
				map[x][y] = 0;
			}
			
			string.append(cube[5]).append("\n");
		}
		System.out.println(string);
	}
	
	private static void rolling(int d) {
		int[] tmp = new int[6];
		
		if(d == 1) { // 동
			tmp[0] = cube[1]; //아랫면
			tmp[1] = cube[5]; // 동
			tmp[2] = cube[0]; // 서
			tmp[3] = cube[3]; // 북
			tmp[4] = cube[4]; // 남
			tmp[5] = cube[2]; // 윗면		
		}
		else if(d == 2) { // 서
			tmp[0] = cube[2];
			tmp[1] = cube[0];
			tmp[2] = cube[5];
			tmp[3] = cube[3];
			tmp[4] = cube[4];
			tmp[5] = cube[1];				
		}
		else if(d == 3) { // 북
			tmp[0] = cube[3];
			tmp[1] = cube[1];
			tmp[2] = cube[2];
			tmp[3] = cube[5];
			tmp[4] = cube[0];
			tmp[5] = cube[4];				
		}
		else if(d == 4) { // 남
			tmp[0] = cube[4];
			tmp[1] = cube[1];
			tmp[2] = cube[2];
			tmp[3] = cube[0];
			tmp[4] = cube[5];
			tmp[5] = cube[3];				
		}
		
		for (int i = 0; i < tmp.length; i++) {
			cube[i] = tmp[i];
		}
	}
}
