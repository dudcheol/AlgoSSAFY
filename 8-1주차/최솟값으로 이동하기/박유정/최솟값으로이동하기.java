package study;

import java.io.*;
import java.util.*;

public class 최솟값으로이동하기 {
	static int W, H, N;
	static int[][] move;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= test; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());

			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			move = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				move[i][0] = Integer.parseInt(st.nextToken()) - 1;
				move[i][1] = Integer.parseInt(st.nextToken()) - 1;
			}

			int min = 0;// 이동거리 최솟값 담을 배열

			for (int i = 0; i < N - 1; i++) {
				int w = move[i][0];
				int h = move[i][1];
				int n = move[i + 1][0] - w;
				int m = move[i + 1][1] - h;

				if (n * m > 0) {// 왼쪽위로 이동하거나 오른쪽 아래로 이동할때 대각 선가능
					int big = Math.max(Math.abs(n), Math.abs(m));
					int small = Math.min(Math.abs(n), Math.abs(m));
					min += small;
					min += big - small;
				} else {
					min += Math.abs(n) + Math.abs(m);
				}
			}
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}
}