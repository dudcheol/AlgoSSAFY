package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14890 경사로 https://www.acmicpc.net/problem/14890
public class BOJ14890_경사로 {
	static int N, L, cnt;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for (int i = 0; i < N; i++) {
			search(i, 0); // 1. 가로로 탐색
			
			search(i, 1); // 2. 세로로 탐색
		}
		System.out.println(cnt);
	}

	private static void search(int i, int d) {
		int[] height = new int[N]; // 높이 저장할 배열
		boolean[] visited = new boolean[N]; // 경사로 놓았는지 아닌지 체크해야 함
		
		for (int j = 0; j < N; j++) {
			if(d == 0)
				height[j] = map[i][j];
			else
				height[j] = map[j][i];
		}
		

		for (int j = 1; j < N; j++) {
			if(Math.abs(height[j - 1] - height[j]) > 1)  // 차이가 1칸보다 높으면 return
				return;
			else if(height[j - 1] + 1 == height[j]) { // 더 높은 블록 만남
				for (int k = j - 1; k > j - 1 - L; k--) {
					if(k < 0 || height[j - 1] != height[k] || visited[k]) // 이미 경사로 놓여있거나, 높이 달라지거나, 범위 밖으로 넘어가면
						return;
					visited[k] = true;
				}

			}
			else if(height[j - 1] - 1 == height[j]) { // 더 낮은 블록 만남
				for (int k = j; k < j + L; k++) {
					if(k > N - 1|| height[j] != height[k] || visited[k])
						return;
					visited[k] = true;
				}
			}
				
			if(j == N - 1)
				cnt++;
		}
		
	}

}
