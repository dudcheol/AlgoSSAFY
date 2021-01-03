package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 12100 2048 (Easy) https://www.acmicpc.net/problem/12100
public class BOJ12100_2048 {
	static int N, result;
	static int[][] map; 
	static boolean[][] merged;
	// 블록 클래스 -> 이미 합쳐진 블록인지 나타내는 flag 만들기
	// 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 4개의 방향에 대해서 랜덤으로 움직이기
		// 2. 일단 볼록을 움직이는 방향으로 쏠려놓기
		// 3. 합칠 수 있는 블록 합치기
		
		/// 재귀로 짜서 이전에 상어할 때처럼 모든 경우의 수를 다 따져보기
		
		for (int d = 0; d < 4; d++) {
			move(map, d, 0);
		}
		
		System.out.println(result);
		
	}
	private static void move(int[][] map2, int d, int count) {
		if(count == 5) {
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(max < map2[i][j])
						max = map2[i][j];
				}
			}
			
			if(result < max)
				result = max;
			return;
		}
		
		int[][] cmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cmap[i][j] = map2[i][j];
			}
		}
		
		merged = new boolean[N][N];
		
		//////////////////////////////////
		if(d == 0){ // 방향이 위라면 
			// for문으로 검사하면서 0이 있으면 그 아래 블럭을 위로 올림
			for (int j = 0; j < N; j++) { // 세로 방향으로 탐색
				for (int i = 1; i <= N; i++) {
					boolean flag = false;
					if(cmap[i - 1][j] == 0) {
						int distance = 0;
						for (int i2 = i; i2 <= N; i2++) {
							// 해당 위치에서 아래로 탐색하면서 0이 아닌 곳을 발견하면
							if(!flag && cmap[i2 - 1][j] != 0) {
								flag = true;
								distance = i2 - i; // 거리 계산
							}
							if(flag) {
								// distance이용해서 아래에 있는 것들 끌어올리기
								cmap[i2 - 1 - distance][j] = cmap[i2 - 1][j]; // 해당 위치에 0아닌 값 넣어줌
								// 끌어올려진 곳은 0으로
								cmap[i2 - 1][j] = 0;
							}
						}
						flag = false;	
						cmap[N - 1][j] = 0;
					}
				}
			}
			
			// 합칠 수 있는 블럭이면 합치기
			for (int j = 0; j < N; j++) {
				for (int i = 1; i < N; i++) {
					if(cmap[i - 1][j] == cmap[i][j] && !merged[i - 1][j] && !merged[i][j]) {
						cmap[i - 1][j] += cmap[i][j];
						for (int i2 = i; i2 < N - 1; i2++) {
							cmap[i2][j] = cmap[i2 + 1][j];
						}
						cmap[N - 1][j] = 0;
						merged[i - 1][j] = true;
					}
				}
			}
		}
		
		//////////////////////////////////////////////
		if(d == 1){ // 방향이 왼쪽이라면
			
			for (int i = 0; i < N; i++) { // 가로 방향으로 탐색
				for (int j = 1; j <= N; j++) {
					
					boolean flag = false;
					if(cmap[i][j - 1] == 0) {
						int distance = 0;
						for (int j2 = j; j2 <= N; j2++) {
							// 0 아닌 곳을 발견
							if(!flag && cmap[i][j2 - 1] != 0) {
								flag = true;
								distance = j2 - j; // 거리 계산
							}
							if(flag) {
								// distance이용해서 오른쪽에 있는 것들 끌어오기
								cmap[i][j2 - 1 - distance] = cmap[i][j2 - 1]; // 해당 위치에 0아닌 값 넣어줌
								// 끌어올려진 곳은 0으로
								cmap[i][j2 - 1] = 0;
							}
						}
						flag = false;	
						cmap[i][N - 1] = 0;
					}
				}
			}
			
			// 합칠 수 있는 블럭이면 합치기
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if(cmap[i][j - 1] == cmap[i][j] && !merged[i][j - 1] && !merged[i][j]) {
						cmap[i][j - 1] += cmap[i][j];
						for (int j2 = j; j2 < N - 1; j2++) {
							cmap[i][j2] = cmap[i][j2 + 1];
						}
						cmap[i][N - 1] = 0;
						merged[i][j - 1] = true;
					}
				}
			}
		}
		
		
		///////////////////////////////////////////////////////
		if(d == 2){ // 방향이 아래라면 
			
			for (int j = 0; j < N; j++) { // 세로 방향으로 탐색
				for (int i = N - 2; i >= -1; i--) {
					
					boolean flag = false;
					if(cmap[i + 1][j] == 0) {
						int distance = 0;
						for (int i2 = i; i2 >= -1; i2--) {
							// 0 아닌 곳 발견
							if(!flag && cmap[i2 + 1][j] != 0) {
								flag = true;
								distance = i - i2; // 거리 계산
							}
							if(flag) {
								// distance이용해서 위에 있는 것들 끌어내리기
								cmap[i2 + 1 + distance][j] = cmap[i2 + 1][j]; // 해당 위치에 0아닌 값 넣어줌
								// 끌어올려진 곳은 0으로
								cmap[i2 + 1][j] = 0;
							
							}
						}
						flag = false;
						cmap[0][j] = 0;
					}
				}
			}
			
			// 합칠 수 있는 블럭이면 합치기
			for (int j = 0; j < N; j++) {
				for (int i = N - 2; i >= 0; i--) {
					if(cmap[i + 1][j] == cmap[i][j] && !merged[i + 1][j] && !merged[i][j]) {
						cmap[i + 1][j] += cmap[i][j];
						for (int i2 = i - 1; i2 >= 0; i2--) {
							cmap[i2 + 1][j] = cmap[i2][j];
						}
						cmap[0][j] = 0;
						merged[i + 1][j] = true;
					}
				}
			}
		}
		
		///////////////////////////////////////////////
		if(d == 3){ // 방향이 오른쪽이라면

			 for (int i = 0; i < N; i++) { // 세로 방향으로 탐색
				for (int j = N - 2; j >= -1; j--) {
					
					boolean flag = false;
					if(cmap[i][j + 1] == 0) {
						int distance = 0;
						for (int j2 = j; j2 >= -1; j2--) {
							// 0 아닌 곳 발견
							if(!flag && cmap[i][j2 + 1] != 0) {
								flag = true;
								distance = j - j2; // 거리 계산
							}
							if(flag) {
								// distance이용해서 위에 있는 것들 끌어내리기
								cmap[i][j2 + 1 + distance] = cmap[i][j2 + 1]; // 해당 위치에 0아닌 값 넣어줌
								// 끌어올려진 곳은 0으로
								cmap[i][j2 + 1] = 0;
							}
						}
						flag = false;
						cmap[i][0] = 0;
					}
				}
			}
			
			// 합칠 수 있는 블럭이면 합치기
			 for (int i = 0; i < N; i++) {
				 for (int j = N - 2; j >= 0; j--) {
					if(cmap[i][j + 1] == cmap[i][j] && !merged[i][j + 1] && !merged[i][j]) {
						cmap[i][j + 1] += cmap[i][j];
						for (int j2 = j - 1; j2 >= 0; j2--) {
							cmap[i][j2 + 1] = cmap[i][j2];
						}
						cmap[i][0] = 0;
						merged[i][j + 1] = true;
					}
				}
			}
		}

//		print(cmap, d, count);
		
		for (int dir = 0; dir < 4; dir++) {
			move(cmap, dir, count + 1);
		}
		
		
	}
	
	
	private static void print(int[][] map2, int d, int count) {
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println("dir:" + d + " count" + count);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
