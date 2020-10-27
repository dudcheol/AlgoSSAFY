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
		int top = 0;
		int topIdx = 0;
		for (int i = 0; i < W; i++) {
			b[i] = Integer.parseInt(st.nextToken());
			if (top < b[i]) {
				top = b[i];
				topIdx = i;
			}
		}

		// top을 기준으로 왼쪽, 오른쪽 계산하기
		int pre = topIdx;
		int ltcnt = 0;
		for (int i = topIdx - 1; i >= 0; i--) {
			if (b[i] <= b[pre]) {
				ltcnt++;
			} else {
				int lim = pre + ltcnt;
				int h = Math.min(b[lim], b[i]);
				for (int j = pre; j < lim; j++) {
					int cal = h - b[j];
					if (cal > 0)
						cnt += cal;
				}
				ltcnt = 0;
			}
			pre = i;
		}
		
		pre = topIdx;
		ltcnt = 0;
		for (int i = topIdx + 1; i < W; i++) {
			if (b[i] <= b[pre]) {
				ltcnt++;
			} else {
				int lim = pre - ltcnt;
				int h = Math.min(b[lim], b[i]);
				for (int j = lim + 1; j <= pre; j++) {
					int cal = h - b[j];
					if (cal > 0)
						cnt += cal;
				}
				ltcnt = 0;
			}
			pre = i;
		}

		System.out.println(cnt);
	}

}
