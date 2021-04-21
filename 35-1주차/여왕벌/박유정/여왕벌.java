package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌 {
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[M][M];

		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], 1);
		}

		int[] larva = new int[2 * M - 1];

		while (N > 0) {
			N--;

			st = new StringTokenizer(br.readLine());

			int index = 0;
			int zero = 0;

			for (int i = 0; i <= 2; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (i == 0) {
					index = num;
					zero = num;
					continue;
				}
				for (int j = 0; j < num; j++) {
					larva[index++] = i;
				}
			}
			// 애벌레 자람 0인건 패스
			index = zero;
			for (int i = zero; i < M; i++) {
				map[M - 1 - i][0] += larva[index++];
			}

			int start = zero > M ? zero - M + 1 : 1;
			for (int i = start; i < M; i++) {
				map[0][i] += larva[index++];
			}
		}

		// 나머지 애벌레 자람
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				map[i][j] = map[i - 1][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
