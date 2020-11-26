package week17;

import java.util.*;
import java.io.*;

public class _1976_여행가자 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] adj = new int[N+1][N+1];
		int[] plan = new int[M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
//				if(i==j) adj[i][j] = 1; // 오답!! 왜???
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(adj[i][k] == 1 && adj[k][j] == 1) {
						adj[i][j] = adj[j][i] = 1;
					}
				}
			}
		}
		
		// 반례 : 모든 섬이 연결되어 있지 않더라도, 1 -> 1 로 이동하는 것은 가능하다
		/*
		3
		2
		0 0 0
		0 0 0
		0 0 0
		1 1
		답:YES
		 */
		for (int i = 1; i <= N; i++) {
			adj[i][i] = 1;
		}
		
		for (int i = 0; i < M-1; i++) {
			if(adj[plan[i]][plan[i+1]] != 1) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
