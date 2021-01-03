package week13;

import java.util.*;
import java.io.*;

public class 빗물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int[] b = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		// 현재 위치에서 고이는 물 확인하기
		for (int i = 0; i < W; i++) {
			// 현재 위치에서 왼쪽으로 가장 큰 기둥찾기
			int leftMax = 0;
			for (int j = i - 1; j >= 0; j--) {
				leftMax = Math.max(leftMax, b[j]);
			}

			// 현재 위치에서 오른쪽으로 가장 큰 기둥 찾기
			int rightMax = 0;
			for (int j = i + 1; j < W; j++) {
				rightMax = Math.max(rightMax, b[j]);
			}

			// 왼쪽과 오른쪽 가장 큰 기둥 중 작은 기둥의 높이 구하기
			int h = Math.min(leftMax, rightMax);

			// 현재 기둥은 h기둥에 의해 잠겨질 수 있으므로 채워지는 물의 양을 구한다
			int cal = h - b[i];
			if (cal > 0)
				cnt += cal;
		}

		System.out.println(cnt);
	}

}
