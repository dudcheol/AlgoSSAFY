package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _3349_최솟값으로이동하기 {
	static int W, H, N;

	public static void main(String[] args) throws Exception {
		// W개 남북방향 도로 . 서->동 1,2,3,...W 열
		// H개 동서방향 도로 . 남->북 1,2,3,...H 행
		// 북동방향 도로
		// N개 교차로를 순서대로 이동 i번째 이동 -> xi,yi
		// 교차로 한번 이동 1 비용
		// xn,yn 까지 가는 비용 최소비용찾기
		// W, H, N(2 ≤ W, H ≤ 10,000, 1 ≤ N ≤ 1,000)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			int min = 0;

			// 시작지점
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int startX = Integer.parseInt(st.nextToken()) - 1;

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				// 도착지점 입력받기
				int nextY = Integer.parseInt(st.nextToken()) - 1;
				int nextX = Integer.parseInt(st.nextToken()) - 1;

				int res = 0;
				if ((startY > nextY && startX < nextX) || (startY < nextY && startX > nextX)) // 1,3사분면인지 확인
					res = Math.abs(startY - nextY) + Math.abs(startX - nextX);
				else // 2,4분면 or 경계선
					res = Math.max(Math.abs(startY - nextY), Math.abs(startX - nextX));

				min += res;

				// 도착지점을 시작지점으로 업데이
				startY = nextY;
				startX = nextX;
			}

			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}
}
