package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10836_여왕벌 {
	static int M, N, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 1); // 애벌레 채우기
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int zero_cnt = Integer.parseInt(st.nextToken());
			int one_cnt = Integer.parseInt(st.nextToken());
			int two_cnt = Integer.parseInt(st.nextToken());
			
			// 애벌레 키우기 (왼쪽 아래 -> 오른쪽 위까지)
			// 왼쪽 아래 -> 왼쪽 위
			for (int j = N - 1; j >= 0; j--) {
				if(zero_cnt != 0) {
					zero_cnt--;
				} else if(one_cnt != 0) {
					one_cnt--;
					map[j][0]++;
				} else if(two_cnt != 0) {
					two_cnt--;
					map[j][0] += 2;
				}
			}
			
			// 왼쪽 위 -> 오른쪽 위
			for (int j = 1; j < N; j++) {
				if(zero_cnt != 0) {
					zero_cnt--;
				} else if(one_cnt != 0) {
					one_cnt--;
					map[0][j]++;
				} else if(two_cnt != 0) {
					two_cnt--;
					map[0][j] += 2;
				}
			}
		}
		
		// 왼쪽, 왼쪽 위, 위와 비교해서 가장 큰 값
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
//				map[i][j] = Math.max(map[i - 1][j], Math.max(map[i - 1][j - 1], map[i][j - 1]));
				map[i][j] = map[i - 1][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
