package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_여행가자 {
	static int N, M, map[][], order[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i == j)
					map[i][j] = 1;
			}
		}
		
		for (int j = 0; j < N; j++) { // 경
			for (int i = 0; i < N; i++) { // 출
				for (int k = 0; k < N; k++) { // 도
					if(map[i][j] == 1 && map[j][k] == 1)
						map[i][k] = 1;
				}
			}
		}
		
		order = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < M; i++) {
			if(map[order[i - 1] - 1][order[i] - 1] != 1) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
